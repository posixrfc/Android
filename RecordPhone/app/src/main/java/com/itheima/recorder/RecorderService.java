package com.itheima.recorder;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class RecorderService extends Service {
	private MediaRecorder recorder;
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		//拿到电话管理器
		TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		//监听电话状态
		//events:决定PhoneStateListener侦听什么内容
		tm.listen(new MyListener(), PhoneStateListener.LISTEN_CALL_STATE);
	}
	
	class MyListener extends PhoneStateListener
	{
		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			// TODO Auto-generated method stub
			super.onCallStateChanged(state, incomingNumber);
			
			switch (state) {
			case TelephonyManager.CALL_STATE_IDLE:
				System.out.println("空闲");
				if(recorder != null){
					recorder.stop();
					recorder.release();
					recorder = null;
				}
				break;
			case TelephonyManager.CALL_STATE_RINGING:
				System.out.println("响铃");
				if(recorder == null){
					recorder = new MediaRecorder();
					recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
					recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
					recorder.setOutputFile("sdcard/luyin.3gp");
					recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
					try {
						recorder.prepare();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				break;
			case TelephonyManager.CALL_STATE_OFFHOOK:
				System.out.println("摘机");
				//开始录音
				if(recorder != null){
					recorder.start();
				}
				break;

			}
		}
		
	}
}