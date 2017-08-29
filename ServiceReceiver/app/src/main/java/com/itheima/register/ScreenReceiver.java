package com.itheima.register;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ScreenReceiver extends BroadcastReceiver
{
	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if(Intent.ACTION_SCREEN_OFF.equals(action)){
			System.out.println("屏幕关闭");
		}
		if(Intent.ACTION_SCREEN_ON.equals(action)){
			System.out.println("屏幕打开");
		}
	}

}
