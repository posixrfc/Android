package com.itheima.showdata;

import java.util.ArrayList;
import java.util.List;

import com.itheima.showdata.domain.Person;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.widget.LinearLayout;
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
        cursor.close();
        db.close();
        
        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
        for (Person p : personList)
        {
        	//1.集合中每有一条元素，就new一个textView
			TextView tv = new TextView(this);
			//2.把人物的信息设置为文本框的内容
			tv.setText(p.toString());
			tv.setTextSize(18);
			//3.把textView设置为线性布局的子节点
			ll.addView(tv);
		}
    }


    
}
