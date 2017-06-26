package com.itheima.startservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service
{

	@Override
	public IBinder onBind(Intent intent) {
		Log.e("0", "onBind");
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.e("0", "create方法");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		Log.e("0", "startCommand方法");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();
		Log.e("0", "destroy方法");
	}
}