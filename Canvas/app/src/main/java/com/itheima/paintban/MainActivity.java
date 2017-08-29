package com.itheima.paintban;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ImageView iv;
	int startX;
	int startY;
	private Canvas canvas;
	private Paint paint;
	private Bitmap bmCopy;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//加载画画板的背景图
		Bitmap bmSrc = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
		
		bmCopy = Bitmap.createBitmap(bmSrc.getWidth(), bmSrc.getHeight(), bmSrc.getConfig());
		paint = new Paint();
		canvas = new Canvas(bmCopy);
		//绘制
		canvas.drawBitmap(bmSrc, new Matrix(), paint);
		
		iv = (ImageView) findViewById(R.id.iv);
		iv.setImageBitmap(bmCopy);
		iv.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int action = event.getAction();
				switch (action) {
				case MotionEvent.ACTION_DOWN:
					startX = (int) event.getX();
					startY = (int) event.getY();
					break;
				case MotionEvent.ACTION_MOVE:
					int x = (int) event.getX();
					int y = (int) event.getY();
					canvas.drawLine(startX, startY, x, y, paint);
					startX = x;
					startY = y;
					iv.setImageBitmap(bmCopy);
					break;
				case MotionEvent.ACTION_UP:
					break;
				}
				//true：告诉系统，这个触摸事件由我来处理
				//false：告诉系统，这个触摸事件我不处理，这时系统会把触摸事件传递给imageview的父节点
				return true;
			}
		});
	}

	public void red(View v){
		paint.setColor(Color.RED);
	}
	public void green(View v){
		paint.setColor(Color.GREEN);
	}
	public void brush(View v){
		paint.setStrokeWidth(7);
	}
	public void save(View v){
		File file = new File("sdcard/dazuo.png");
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		bmCopy.compress(CompressFormat.PNG, 100, fos);
		//发送sd卡就绪广播
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_MEDIA_MOUNTED);
		intent.setData(Uri.fromFile(Environment.getExternalStorageDirectory()));
		sendBroadcast(intent);
	}
}