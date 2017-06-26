package com.itheima.senddata;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		Intent intent = getIntent();
		String maleName = intent.getStringExtra("malename");
		String feMaleName = intent.getStringExtra("femalename");
		
//		Bundle bundle = intent.getExtras();
//		String maleName = bundle.getString("malename");
//		String feMaleName = bundle.getString("femalename");
		
		Random rd = new Random();
		int yinyuan = rd.nextInt(100);
		
		TextView tv = (TextView) findViewById(R.id.tv);
		tv.setText(maleName + "和" + feMaleName + "的姻缘值为" + yinyuan);
	}
}
