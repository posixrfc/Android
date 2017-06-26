package com.itheima.getresult;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	public void click(View c)
	{
		Intent intent = new Intent(this, ContactActivity.class);
		startActivityForResult(intent, 10);
	}
	public void click2(View v)
	{
		Intent intent = new Intent(this, CallbackActivity.class);
		startActivityForResult(intent, 20);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		String name = data.getStringExtra("name");
		if(requestCode == 10)
		{
			EditText et = (EditText)findViewById(R.id.et);
			et.setText(name);
		}
		else if(requestCode == 20)
		{
			EditText et_content = (EditText)findViewById(R.id.et_content);
			et_content.setText(name);
		}
	}
}