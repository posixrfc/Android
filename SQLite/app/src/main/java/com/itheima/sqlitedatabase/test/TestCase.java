package com.itheima.sqlitedatabase.test;

import com.itheima.sqlitedatabase.MyOpenHelper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

public class TestCase extends AndroidTestCase {

	//此时测试框架还没有初始化完毕，没有虚拟上下文对象
//	private MyOpenHelper oh = new MyOpenHelper(getContext(), "people.db", null, 1);
	private MyOpenHelper oh;
	private SQLiteDatabase db;
	public void test(){
		//getContext():获取一个虚拟的上下文
		MyOpenHelper oh = new MyOpenHelper(getContext(), "people.db", null, 1);
		//如果数据库不存在，先创建数据库，再获取可读可写的数据库对象，如果数据库存在，就直接打开
		SQLiteDatabase db = oh.getWritableDatabase();
		//如果存储空间满了，那么返回只读数据库对象
//		SQLiteDatabase db = oh.getReadableDatabase();
	}
	
	//测试框架初始化完毕之后，在测试方法执行之前，此方法调用
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		oh = new MyOpenHelper(getContext(), "people.db", null, 1);
		db = oh.getWritableDatabase();
	}

	//测试方法执行完毕之后，此方法调用
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		db.close();
	}
	
	public void insert(){
		
//		db.execSQL("insert into person (name, salary, phone)values(?, ?, ?)", new Object[]{"小志的老婆[1]", "13000", 138438});
//		db.execSQL("insert into person (name, salary, phone)values(?, ?, ?)", new Object[]{"小志的儿子", 14000, "13888"});
		db.execSQL("insert into person (name, salary, phone)values(?, ?, ?)", new Object[]{"小志", 14000, "13888"});
	}
	
	public void delete(){
		db.execSQL("delete from person where name = ?", new Object[]{"小志"});
	}
	
	public void update(){
		db.execSQL("update person set phone = ? where name = ?", new Object[]{186666, "小志的儿子"});
	}
	
	public void select(){
		Cursor cursor = db.rawQuery("select name, salary from person", null);
		
		while(cursor.moveToNext()){
			//通过列索引获取列的值
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String salary = cursor.getString(1);
			System.out.println(name + ";" + salary);
		}
	}
	
	public void insertApi(){
		//把要插入的数据全部封装至ContentValues对象
		ContentValues values = new ContentValues();
		values.put("name", "游天龙");
		values.put("phone", "15999");
		values.put("salary", 16000);
		db.insert("person", null, values);
	}
	
	public void deleteApi(){
		int i = db.delete("person", "name = ? and _id = ?", new String[]{"小志的儿子", "3"});
		System.out.println(i);
	}
	
	public void updateApi(){
		ContentValues values = new ContentValues();
		values.put("salary", 26000);
		int i = db.update("person", values, "name = ?", new String[]{"游天龙"});
		System.out.println(i);
	}
	
	public void selectApi(){
		Cursor cursor = db.query("person", null, null, null, null, null, null, null);
		while(cursor.moveToNext()){
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String phone = cursor.getString(cursor.getColumnIndex("phone"));
			String salary = cursor.getString(cursor.getColumnIndex("salary"));
			System.out.println(name + ";" + phone + ";" + salary);
		}
	}
	
	public void transaction(){
		try{
			//开启事务
			db.beginTransaction();
			ContentValues values = new ContentValues();
			values.put("salary", 12000);
			db.update("person", values, "name = ?", new String[]{"小志"});
			
			values.clear();
			values.put("salary", 16000);
			db.update("person", values, "name = ?", new String[]{"小志的儿子"});
			
			int i = 3/0;
			//设置  事务执行成功
			db.setTransactionSuccessful();
		}
		finally{
			//关闭事务，同时提交，如果已经设置事务执行成功，那么sql语句就生效了，反之，sql语句回滚
			db.endTransaction();
		}
	}
}
