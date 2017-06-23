package com.ithiema.imageviewer;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity
{
	static ImageView iv;
	static MainActivity ma;
	static Handler handler = new Handler() {
		public void handleMessage(Message msg) {//此方法在主线程中调用，可以用来刷新ui
			switch (msg.what)
			{
			case 1:
				iv.setImageBitmap((Bitmap)msg.obj);
				break;
			case 0:
				Toast.makeText(ma, "请求失败", Toast.LENGTH_SHORT).show();
			}
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv = (ImageView) findViewById(R.id.iv);
		ma = this;
	}

	public void click(View v)
	{
		new Thread() {
			@Override
			public void run()
			{
				String path = "http://172.24.176.79:8080/Mercurial/kaihu_web_right.png";
				try
				{
					URL url = new URL(path);
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.setConnectTimeout(5000);
					conn.setReadTimeout(5000);
					conn.connect();
					if(conn.getResponseCode() == 200)
					{
						InputStream is = conn.getInputStream();
						Bitmap bm = BitmapFactory.decodeStream(is);
						Message msg = new Message();
						msg.obj = bm;
						msg.what = 1;
						handler.sendMessage(msg);//把消息发送至主线程的消息队列
					}
					else
					{
						Toast.makeText(MainActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
						Message msg = handler.obtainMessage();
						msg.what = 0;
						handler.sendMessage(msg);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
}