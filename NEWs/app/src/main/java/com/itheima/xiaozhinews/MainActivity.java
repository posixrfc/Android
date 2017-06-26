package com.itheima.xiaozhinews;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.itheima.xiaozhinews.domain.News;
import com.loopj.android.image.SmartImageView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.util.Xml;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	List<News> newsList;
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			ListView lv = (ListView) findViewById(R.id.lv);
			lv.setAdapter(new MyAdapter());
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getNewsInfo();
	}

	class MyAdapter extends BaseAdapter
	{
		@Override
		public int getCount() {
			return newsList.size();
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			News news = newsList.get(position);
			View v = null;
			ViewHolder mHolder;
			if(convertView == null)
			{
				v = View.inflate(MainActivity.this, R.layout.item_listview, null);
				mHolder = new ViewHolder();
				mHolder.tv_title = (TextView) v.findViewById(R.id.tv_title);
				mHolder.tv_detail = (TextView) v.findViewById(R.id.tv_detail);
				mHolder.tv_comment = (TextView) v.findViewById(R.id.tv_comment);
				mHolder.siv = (SmartImageView) v.findViewById(R.id.iv);
				v.setTag(mHolder);
			}
			else
			{
				v = convertView;
				mHolder = (ViewHolder) v.getTag();
			}
			mHolder.tv_title.setText(news.getTitle());
			mHolder.tv_detail.setText(news.getDetail());
			mHolder.tv_comment.setText(news.getComment() + "条评论");
			mHolder.siv.setImageUrl(news.getImageUrl());
			return v;
		}
		
		class ViewHolder
		{
			TextView tv_title;
			TextView tv_detail;
			TextView tv_comment;
			SmartImageView siv;
		}
		
		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}
	}
	private void getNewsInfo()
	{
		new Thread(){
			@Override
			public void run() {
				String path = "http://192.168.13.13:8080/news.xml";
				try {
					URL url = new URL(path);
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.setConnectTimeout(5000);
					conn.setReadTimeout(5000);
					if(conn.getResponseCode() == 200)
					{
						InputStream is = conn.getInputStream();
						parseNewsXml(is);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
	
	private void parseNewsXml(InputStream is)
	{
		XmlPullParser xp = Xml.newPullParser();
		try {
			xp.setInput(is, "utf-8");
			int type = xp.getEventType();
			News news = null;
			while(type != XmlPullParser.END_DOCUMENT){
				switch (type) {
				case XmlPullParser.START_TAG:
					if("newslist".equals(xp.getName())){
						newsList = new ArrayList<News>();
					}
					else if("news".equals(xp.getName())){
						news = new News();
					}
					else if("title".equals(xp.getName())){
						String title = xp.nextText();
						news.setTitle(title);
					}
					else if("detail".equals(xp.getName())){
						String detail = xp.nextText();
						news.setDetail(detail);
					}
					else if("comment".equals(xp.getName())){
						String comment = xp.nextText();
						news.setComment(comment);
					}
					else if("image".equals(xp.getName())){
						String image = xp.nextText();
						news.setImageUrl(image);
					}
					break;
				case XmlPullParser.END_TAG:
					if("news".equals(xp.getName())){
						newsList.add(news);
					}
					break;

				}
				type = xp.next();
			}
			handler.sendEmptyMessage(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}