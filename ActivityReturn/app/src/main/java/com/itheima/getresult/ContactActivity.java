package com.itheima.getresult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ContactActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);
		ListView lv = (ListView) findViewById(R.id.lv);
		final String[] objects = {"小志", "逼哥", "世界级XXX", "国服第一"};
		lv.setAdapter(new ArrayAdapter<String>(this, R.layout.item_listview, R.id.tv, objects));
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent data = new Intent();
				data.putExtra("name", objects[position]);
				setResult(1, data);
				finish();
			}
		});
	}
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Log.e("0", "onBackPressed");
	}
}
