package com.itheima.sqlitedatabase.test;


import com.itheima.showdata.MyOpenHelper;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

public class TestCase extends AndroidTestCase {

	private MyOpenHelper oh;
	private SQLiteDatabase db;
	//测试框架初始化完毕之后，在测试方法执行之前，此方法调用
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		oh = new MyOpenHelper(getContext());
		db = oh.getWritableDatabase();
	}

	//测试方法执行完毕之后，此方法调用
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		db.close();
	}
	
	
	public void insertApi(){
		//把要插入的数据全部封装至ContentValues对象
		for (int i = 0; i < 50; i++) {
			ContentValues values = new ContentValues();
			values.put("name", "赵"+i);
			values.put("phone", "159"+i+i);
			values.put("salary", "160"+i+i);
			db.insert("person", null, values);
		}
	}
	
	
}