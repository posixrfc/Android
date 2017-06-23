package com.ithiema.imageviewer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.ithiema.cacheimageviewer.R;

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
		public void handleMessage(android.os.Message msg) {
			switch (msg.what)
			{
			case 1:
				iv.setImageBitmap((Bitmap)msg.obj);
				break;
			case 0:
				Toast.makeText(ma, "请求失败", Toast.LENGTH_SHORT).show();
				break;
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
		final String path = "http://172.24.176.79:8080/Mercurial/kaihu_web_right.png";
		final File file = new File(getCacheDir(), getFileName(path));
		if(file.exists())
		{
			System.out.println("从缓存读取的");
			Bitmap bm = BitmapFactory.decodeFile(file.getAbsolutePath());
			iv.setImageBitmap(bm);
            return;
		}
        System.out.println("从网上下载的");
        new Thread() {
            @Override
            public void run()
            {
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
                        FileOutputStream fos = new FileOutputStream(file);
                        byte[] b = new byte[1024];
                        int len;
                        while((len = is.read(b)) != -1){
                            fos.write(b, 0, len);
                        }
                        fos.close();
                        Bitmap bm = BitmapFactory.decodeFile(file.getAbsolutePath());
                        Message msg = new Message();
                        msg.obj = bm;
                        msg.what = 1;
                        handler.sendMessage(msg);
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
	public String getFileName(String path)
	{
		int index = path.lastIndexOf("/");
		return path.substring(index + 1);
	}
}