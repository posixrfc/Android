package com.itheima.showdata;

import java.util.ArrayList;
import java.util.List;

import com.itheima.listviewshowdata.R;
import com.itheima.showdata.domain.Person;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity
{
	List<Person> personList;
    @Override
    protected void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        personList = new ArrayList<Person>();
        //把数据库的数据查询出来
        MyOpenHelper oh = new MyOpenHelper(this);
        SQLiteDatabase db =  oh.getWritableDatabase();
        Cursor cursor = db.query("person", null, null, null, null, null, null, null);
        while(cursor.moveToNext())
		{
        	String _id = cursor.getString(0);
        	String name = cursor.getString(1);
        	String salary = cursor.getString(2);
        	String phone = cursor.getString(3);
        	Person p = new Person(_id, name, phone, salary);
        	personList.add(p);
        }
        
        ListView lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(new MyAdapter());
    }
    
    class MyAdapter extends BaseAdapter
	{
    	//系统调用，用来获知集合中有多少条元素
		@Override
		public int getCount() {
			return personList.size();
		}
		//由系统调用，获取一个View对象，作为ListView的条目
		//position:本次getView方法调用所返回的View对象，在listView中是处于第几个条目，那么position的值就是多少
		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			Person p = personList.get(position);
//			TextView tv = new TextView(MainActivity.this);
			System.out.println("getView调用：" + position + ";" + convertView);
//			tv.setText(p.toString());
//			tv.setTextSize(18);
			View v = null;
			//判断条目是否有缓存
			if(convertView == null){
				//把布局文件填充成一个View对象
				v = View.inflate(MainActivity.this, R.layout.item_listview, null);
			}
			else{
				v = convertView;
			}
			//获取布局填充器对象
//			LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
//			使用布局填充器填充布局文件
//			View v2 = inflater.inflate(R.layout.item_listview, null);
			
//			LayoutInflater inflater2 = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
//			View v3 = inflater2.inflate(R.layout.item_listview, null);
			
			//通过资源id查找组件,注意调用的是View对象的findViewById
			TextView tv_name = (TextView) v.findViewById(R.id.tv_name);
			tv_name.setText(p.getName());
			TextView tv_phone = (TextView) v.findViewById(R.id.tv_phone);
			tv_phone.setText(p.getPhone());
			TextView tv_salary = (TextView) v.findViewById(R.id.tv_salary);
			tv_salary.setText(p.getSalary());
			return v;
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
}