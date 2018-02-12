package com.itheima.mobilemultidownload;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.app.Activity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity
{
	static int ThreadCount = 3;
	static int finishedThread = 0;
	
	int currentProgress;
	String fileName = "QQPlayer.exe";
	String path = "http://192.168.13.13:8080/" + fileName;
	private ProgressBar pb;
	TextView tv;
	
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			tv.setText((long)pb.getProgress() * 100 / pb.getMax() + "%");
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		pb = findViewById(R.id.pb);
		tv = findViewById(R.id.tv);
	}

public void click(View v)
{
	new Thread() {
	@Override
	public void run() {
		try {
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(5000);
			if(conn.getResponseCode() == 200) {
				int length = conn.getContentLength();
				pb.setMax(length);
				File file = new File(Environment.getExternalStorageDirectory(), fileName);
				RandomAccessFile raf = new RandomAccessFile(file, "rwd");
				raf.setLength(length);
				raf.close();
				int size = length / ThreadCount;
				for (int i = 0; i < ThreadCount; i++) {
					int startIndex = i * size;
					int endIndex = (i + 1) * size - 1;
					if(i == ThreadCount - 1){
						endIndex = length - 1;
					}
					new DownLoadThread(startIndex, endIndex, i).start();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	}.start();
}
class DownLoadThread extends Thread
{
	int startIndex;
	int endIndex;
	int threadId;
public DownLoadThread(int startIndex, int endIndex, int threadId) {
	this.startIndex = startIndex;
	this.endIndex = endIndex;
	this.threadId = threadId;
}
@Override
public void run()
{
	try {
		File progressFile = new File(Environment.getExternalStorageDirectory(), threadId + ".txt");
		if(progressFile.exists())
		{
			FileInputStream fis = new FileInputStream(progressFile);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			int lastProgress = Integer.parseInt(br.readLine());
			fis.close();
			startIndex += lastProgress;
			currentProgress += lastProgress;
			pb.setProgress(currentProgress);
			handler.sendEmptyMessage(1);
		}
		HttpURLConnection conn;
		URL url = new URL(path);
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(5000);
		conn.setReadTimeout(5000);
		conn.setRequestProperty("Range", "bytes=" + startIndex + "-" + endIndex);
		if(conn.getResponseCode() == 206)
		{
			InputStream is = conn.getInputStream();
			byte[] b = new byte[1024];
			int len;
			int total = 0;
			File file = new File(Environment.getExternalStorageDirectory(), fileName);
			RandomAccessFile raf = new RandomAccessFile(file, "rwd");
			raf.seek(startIndex);
			while((len = is.read(b)) != -1)
			{
				raf.write(b, 0, len);
				total += len;
				System.out.println("线程" + threadId + "下载了" + total);
				currentProgress += len;
				pb.setProgress(currentProgress);
				handler.sendEmptyMessage(1);
				RandomAccessFile progressRaf = new RandomAccessFile(progressFile, "rwd");
				progressRaf.write((total + "").getBytes("ASCII"));
				progressRaf.close();
			}
			System.out.println("线程" + threadId + "下载完毕-------------------小志参上！");
			raf.close();
			finishedThread++;
			synchronized (path) {
				if(finishedThread == ThreadCount) {
					for (int i = 0; i < ThreadCount; i++) {
						new File(Environment.getExternalStorageDirectory(), i + ".txt").delete();
					}
					finishedThread = 0;
				}
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
}//inner run
}//inner class
}//file class