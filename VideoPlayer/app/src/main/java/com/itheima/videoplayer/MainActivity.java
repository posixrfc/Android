package com.itheima.videoplayer;

import java.io.IOException;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class MainActivity extends Activity {
	private MediaPlayer player;
	static int currentPosition;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		SurfaceView sv = (SurfaceView) findViewById(R.id.sv);
		final SurfaceHolder sh = sv.getHolder();
//		Thread t = new Thread(){
//			@Override
//			public void run() {
//				try {
//					sleep(200);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				runOnUiThread(new Runnable() {
//					@Override
//					public void run() {
//						MediaPlayer player = new MediaPlayer();
//						player.reset();
//						try {
//							player.setDataSource("sdcard/2.3gp");
//							player.setDisplay(sh);
//							player.prepare();
//							player.start();
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
//					}
//				});
//			}
//		};
//		t.start();
		sh.addCallback(new Callback() {
			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				if(player != null){
					currentPosition = player.getCurrentPosition();
					player.stop();
					player.release();
					player = null;
				}
			}
			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				if(player == null){
					player = new MediaPlayer();
					player.reset();
					try {
						player.setDataSource("sdcard/2.3gp");
						player.setDisplay(sh);
						player.prepare();
						player.start();
						player.seekTo(currentPosition);
					} catch (Exception e) {
						e.printStackTrace();
					} 
				}
			}
			@Override
			public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}
		});
	}
}