package com.itheima.pullparser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import com.itheima.pullparser.domain.City;

import android.os.Bundle;
import android.app.Activity;
import android.util.Xml;
import android.view.View;

public class MainActivity extends Activity
{
	List<City> cityList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	public void click(View v)
	{
		InputStream is = getClassLoader().getResourceAsStream("weather.xml");
		XmlPullParser xp = Xml.newPullParser();
		try {
			xp.setInput(is, "UTF-8");
			//获取当前节点的事件类型，通过事件类型的判断，我们可以知道当前节点是什么节点，从而确定我们应该做什么操作
			int type = xp.getEventType();
			City city = null;
			while(type != XmlPullParser.END_DOCUMENT)
			{
				switch (type)
				{
				case XmlPullParser.START_TAG:
					if("weather".equals(xp.getName())) {
						cityList = new ArrayList<City>();
					}
					else if(xp.getName().equals("city")) {
						city = new City();
					}
					else if("name".equals(xp.getName())) {
						String name = xp.nextText();
						city.setName(name);
					}
					else if("temp".equals(xp.getName())){
						String temp = xp.nextText();
						city.setTemp(temp);
					}
					else if("pm".equals(xp.getName())){
						String pm = xp.nextText();
						city.setPm(pm);
					}
					break;
				case XmlPullParser.END_TAG:
					if("city".equals(xp.getName())){
						cityList.add(city);
					}
				}
				type = xp.next();
			}
			for (City c : cityList) {
				System.out.println(c.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}