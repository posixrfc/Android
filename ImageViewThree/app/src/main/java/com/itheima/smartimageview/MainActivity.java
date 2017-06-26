package com.itheima.smartimageview;

import com.loopj.android.image.SmartImageView;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void click(View v)
	{
		String path = "http://172.24.176.79:8080/Mercurial/kaihu_web_right.png";
		SmartImageView siv = (SmartImageView) findViewById(R.id.iv);
		siv.setImageUrl(path);
	}
}
