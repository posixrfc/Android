package com.itheima.receivezdy;	

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ZDYReceiver extends BroadcastReceiver
{
	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "成功接收到广播", Toast.LENGTH_SHORT).show();
	}

}
