package com.itheima.sqlitedatabase;

import java.sql.ResultSet;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelper extends SQLiteOpenHelper
{
	public MyOpenHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	//数据库创建时，此方法会调用
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table person(_id integer primary key autoincrement, name char(10), salary char(20), phone integer(20))");

	}

	//数据库升级时，此方法会调用
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		System.out.println("数据库升级了");
	}
}