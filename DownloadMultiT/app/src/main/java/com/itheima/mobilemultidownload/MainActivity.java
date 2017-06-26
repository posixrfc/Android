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
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

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
		pb = (ProgressBar) findViewById(R.id.pb);
		tv = (TextView) findViewById(R.id.tv);
	}

	public void click(View v){
		
		Thread t = new Thread(){
			@Override
			public void run() {
				try {
					URL url = new URL(path);
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.setConnectTimeout(5000);
					conn.setReadTimeout(5000);
					
					if(conn.getResponseCode() == 200){
						int length = conn.getContentLength();
						pb.setMax(length);
						
						File file = new File(Environment.getExternalStorageDirectory(), fileName);
						//生成临时文件
						RandomAccessFile raf = new RandomAccessFile(file, "rwd");
						//设置临时文件的大小
						raf.setLength(length);
						raf.close();
						//计算出每个线程应该下载多少字节
						int size = length / ThreadCount;
						
						for (int i = 0; i < ThreadCount; i++) {
							//计算线程下载的开始位置和结束位置
							int startIndex = i * size;
							int endIndex = (i + 1) * size - 1;
							//如果是最后一个线程，那么结束位置写死
							if(i == ThreadCount - 1){
								endIndex = length - 1;
							}
//							System.out.println("线程" + i + "的下载区间是：" + startIndex + "---" + endIndex);
							new DownLoadThread(startIndex, endIndex, i).start();
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		t.start();
	}
	
	class DownLoadThread extends Thread{
		int startIndex;
		int endIndex;
		int threadId;
		
		public DownLoadThread(int startIndex, int endIndex, int threadId) {
			super();
			this.startIndex = startIndex;
			this.endIndex = endIndex;
			this.threadId = threadId;
		}

		@Override
		public void run() {
			//再次发送http请求，下载原文件
			try {
				File progressFile = new File(Environment.getExternalStorageDirectory(), threadId + ".txt");
				if(progressFile.exists())
				{
					FileInputStream fis = new FileInputStream(progressFile);
					BufferedReader br = new BufferedReader(new InputStreamReader(fis));
					//从进度临时文件中读取出上一次下载的总进度，然后与原本的开始位置相加，得到新的开始位置
					int lastProgress = Integer.parseInt(br.readLine());
					startIndex += lastProgress;
					
					//把上次下载的进度显示至进度条
					currentProgress += lastProgress;
					pb.setProgress(currentProgress);
					
					//发送消息，让主线程刷新文本进度
					handler.sendEmptyMessage(1);
					fis.close();
				}
				System.out.println("线程" + threadId + "的下载区间是：" + startIndex + "---" + endIndex);
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
					int len = 0;
					int total = 0;
					File file = new File(Environment.getExternalStorageDirectory(), fileName);
					RandomAccessFile raf = new RandomAccessFile(file, "rwd");
					raf.seek(startIndex);
					while((len = is.read(b)) != -1)
					{
						raf.write(b, 0, len);
						total += len;
						System.out.println("线程" + threadId + "下载了" + total);
						
						//每次读取流里数据之后，把本次读取的数据的长度显示至进度条
						currentProgress += len;
						pb.setProgress(currentProgress);
						//发送消息，让主线程刷新文本进度
						handler.sendEmptyMessage(1);
						
						//生成一个专门用来记录下载进度的临时文件
						RandomAccessFile progressRaf = new RandomAccessFile(progressFile, "rwd");
						//每次读取流里数据之后，同步把当前线程下载的总进度写入进度临时文件中
						progressRaf.write((total + "").getBytes());
						progressRaf.close();
					}
					System.out.println("线程" + threadId + "下载完毕-------------------小志参上！");
					raf.close();
					
					finishedThread++;
					synchronized (path) {
						if(finishedThread == ThreadCount){
							for (int i = 0; i < ThreadCount; i++) {
								File f = new File(Environment.getExternalStorageDirectory(), i + ".txt");
								f.delete();
							}
							finishedThread = 0;
						}
					}
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}