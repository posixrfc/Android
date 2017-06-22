package com.itheima.xmlserializer;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlSerializer;

import com.itheima.createxml.domain.Message;

import android.os.Bundle;
import android.app.Activity;
import android.util.Xml;
import android.view.View;

public class MainActivity extends Activity
{
	List<Message> smsList;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		smsList = new ArrayList<Message>();
		for (int i = 0; i < 10; i++) {
			Message sms = new Message("小志好棒" + i, System.currentTimeMillis()
					+ "", "138" + i + i, "1");
			smsList.add(sms);
		}
	}

	public void click(View v)
	{
		XmlSerializer xs = Xml.newSerializer();
		File file = new File("sdcard/sms2.xml");
		try {
			FileOutputStream fos = new FileOutputStream(file);
			xs.setOutput(fos, "utf-8");
			xs.startDocument("utf-8", true);
			xs.startTag(null, "message");
			for (Message sms : smsList)
			{
				xs.startTag(null, "sms");
				
				xs.startTag(null, "body");
				xs.text(sms.getBody() + "<body>");
				xs.endTag(null, "body");
				
				xs.startTag(null, "date");
				xs.text(sms.getDate());
				xs.endTag(null, "date");
				
				xs.startTag(null, "type");
				xs.text(sms.getType());
				xs.endTag(null, "type");
				
				xs.startTag(null, "address");
				xs.text(sms.getAddress());
				xs.endTag(null, "address");
				
				xs.endTag(null, "sms");
			}
			xs.endTag(null, "message");
			xs.endDocument();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}