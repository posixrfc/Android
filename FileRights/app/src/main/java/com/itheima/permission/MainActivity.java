package com.itheima.permission;

import java.io.FileOutputStream;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;

@SuppressLint("WorldReadableFiles")
public class MainActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	public void click1(View v)
	{
		//路径已经默认为data/data/com.itheima.permission/files
		try {
			FileOutputStream fos = openFileOutput("info1.txt", MODE_PRIVATE);
			fos.write("哈哈哈".getBytes());
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void click2(View v)
	{
		try {
			@SuppressWarnings("deprecation")
			FileOutputStream fos = openFileOutput("info2.txt", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
			fos.write("ohohoho".getBytes());
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void click3(View v)
	{
		try {
			@SuppressWarnings("deprecation")
			FileOutputStream fos = openFileOutput("info3.txt", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
			fos.write("德玛西亚".getBytes());
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}