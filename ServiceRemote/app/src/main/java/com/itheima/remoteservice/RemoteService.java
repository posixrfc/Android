package com.itheima.remoteservice;

import com.itheima.remoteservice.PublicBusiness.Stub;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class RemoteService extends Service {

	@Override
	public void onCreate() {
		super.onCreate();
		Log.e("RemoteService", "onCreate");
	}

	@Override
	public IBinder onBind(Intent intent) {
		Log.e("RemoteService", "onBind");
		return new furong();
	}

	@Override
	public boolean onUnbind(Intent intent) {
		Log.e("RemoteService", "onUnbind");
		return super.onUnbind(intent);
	}

	class furong extends Stub{
		@Override
		public void qianXian() {
			banzheng();
		}
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.e("RemoteService", "onStartCommand");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		Log.e("RemoteService", "onDestroy");
		super.onDestroy();
	}

	public void banzheng(){
		Log.e("RemoteService", "李局帮你来办证");
	}
}
