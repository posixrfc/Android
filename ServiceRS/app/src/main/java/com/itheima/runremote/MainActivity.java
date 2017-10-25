package com.itheima.runremote;

import com.itheima.remoteservice.PublicBusiness;
import com.itheima.remoteservice.PublicBusiness.Stub;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;

import android.view.View;

public class MainActivity extends Activity
{
	private MyserviceConn conn;
	PublicBusiness pb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		conn = new MyserviceConn();
	}

	public void click(View v){//启动远程服务
		Intent intent = new Intent();
		intent.setAction("com.itheima.remote");
		startService(intent);
	}
	public void click2(View v){//停止远程服务
		Intent intent = new Intent();
		intent.setAction("com.itheima.remote");
		stopService(intent);
	}
	
	public void click3(View v){
		Intent intent = new Intent();
		intent.setAction("com.itheima.remote");
		bindService(intent, conn, BIND_AUTO_CREATE);
	}

	public void click4(View v){
		unbindService(conn);
	}
	class MyserviceConn implements ServiceConnection {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			pb = Stub.asInterface(service);
		}
		@Override
		public void onServiceDisconnected(ComponentName name) {
		}
	}
	
	public void click5(View v) {
		try {
			pb.qianXian();
		} catch (RemoteException e) {
			e.printStackTrace(System.err);
		}
	}
}