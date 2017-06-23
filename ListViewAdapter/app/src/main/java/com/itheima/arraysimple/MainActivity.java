package com.itheima.arraysimple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ListView lv = (ListView) findViewById(R.id.lv);

		String[] objects = {"小志", "小志的儿子", "萌萌"};
		lv.setAdapter(new ArrayAdapter<String>(this, R.layout.item_listview, R.id.tv_name, objects));
		
//		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
//
//		Map<String, Object> map1 = new HashMap<String, Object>();
//		map1.put("photo", R.drawable.photo1);
//		map1.put("name", "小志的儿子");
//		data.add(map1);
//
//		Map<String, Object> map2 = new HashMap<String, Object>();
//		map2.put("photo", R.drawable.photo2);
//		map2.put("name", "小志");
//		data.add(map2);
//
//		Map<String, Object> map3 = new HashMap<String, Object>();
//		map3.put("photo", R.drawable.photo3);
//		map3.put("name", "赵帅哥");
//		data.add(map3);
//
//		lv.setAdapter(new SimpleAdapter(this, data, R.layout.item_listview,
//				new String[]{"photo", "name"}, new int[]{R.id.iv_photo, R.id.tv_name}));
	}


}
