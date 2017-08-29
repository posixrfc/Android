package com.itheima.register;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class RegisterService extends Service {

	private ScreenReceiver receiver;
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate()
	{
		super.onCreate();
		receiver = new ScreenReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_SCREEN_OFF);
		filter.addAction(Intent.ACTION_SCREEN_ON);
		registerReceiver(receiver, filter);
		Log.e("0", "registerReceiver");
	}
	@Override
	public void onDestroy()
	{
		unregisterReceiver(receiver);
		super.onDestroy();
		Log.e("0", "unregisterReceiver");
	}
}
