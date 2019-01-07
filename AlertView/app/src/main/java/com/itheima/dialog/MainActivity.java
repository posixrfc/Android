package com.itheima.dialog;

import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	public void click1(View v)
	{
		AlertDialog.Builder builder = new Builder(this);
		builder.setIcon(android.R.drawable.alert_dark_frame);
		builder.setTitle("欲练此功必先自宫");
		builder.setMessage("李志平，想清楚哦");
		builder.setPositiveButton("确定", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this, "感谢使用本软件,再见", Toast.LENGTH_SHORT).show();
			}
		});
		builder.setNeutralButton("OK", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this, "不知结果怎么样", Toast.LENGTH_SHORT).show();
			}
		});
		builder.setNegativeButton("取消", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this, "若不自宫,一定不成功", Toast.LENGTH_SHORT).show();
			}
		});
		builder.create().show();
	}
	public void click2(View v)
	{
		AlertDialog.Builder builder = new Builder(this);
		builder.setTitle("请选择性别");
		final String[] items = {"男", "女"};
		builder.setSingleChoiceItems(items, 1, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this, "您选择的是:" + items[which], Toast.LENGTH_SHORT).show();
				dialog.dismiss();
			}
		});
		builder.show();
	}
	public void click3(View v)
	{
		AlertDialog.Builder builder = new Builder(this);
		builder.setTitle("请选择您觉得帅的人");
		final String[] items = {"侃哥", "赵帅哥", "赵老师", "赵师兄"};
		final boolean[] checkedItems = {true, true, false, false};
		builder.setMultiChoiceItems(items, checkedItems, new OnMultiChoiceClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				checkedItems[which] = isChecked;
			}
		});
		builder.setPositiveButton("确定", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String text = "";
				for(int i = 0; i < checkedItems.length; i++) {
					text += checkedItems[i] ? items[i] + "," : "";
				}
				if (text.length() != 0) {
					text = text.substring(0, text.length() - 1);
				}
				Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
				dialog.dismiss();
			}
		});
		builder.show();
	}
	public void anyLayout(View v) {
		AlertDialog.Builder builder = new Builder(this);
		View inputView = View.inflate(this, R.layout.layout_input, null);
		builder.setTitle("Valid Token").setIcon(R.drawable.ic_launcher).setView(inputView);
		final EditText textField = inputView.findViewById(R.id.token);
		builder.setPositiveButton("确定", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String value = textField.getText().toString();
				if (null == value || value.length() == 0) {
					value = "token is null";
				}
				Toast.makeText(MainActivity.this, value, Toast.LENGTH_SHORT).show();
			}
		});
		AlertDialog dialog = builder.create();
		//dialog.dismiss();
		dialog.show();
	}
}