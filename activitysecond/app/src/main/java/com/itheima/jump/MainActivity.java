package com.itheima.jump;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /** 隐式跳转：通过指定action和data*/
    public void click1(View v)
    {
    	Intent intent = new Intent();
    	intent.setAction(Intent.ACTION_CALL);
    	intent.setData(Uri.parse("tel:110"));
    	startActivity(intent);
    }
    /**显示跳转：直接指定目标Activity的包名和类名*/
    public void click2(View v)
    {
    	Intent intent = new Intent();
    	intent.setClass(this, SecondActivity.class);
    	startActivity(intent);
    }
    /**显示跳转至拨号器*/
    public void click3(View v)
    {
    	Intent intent = new Intent();
    	intent.setClassName("com.android.dialer", "com.android.dialer.DialtactsActivity");
    	startActivity(intent);
    }
    /** 隐式跳转至拨号器*/
    public void click4(View v)
    {
    	Intent intent = new Intent();
    	intent.setAction(Intent.ACTION_DIAL);
    	startActivity(intent);
    }
    /**隐式跳转至secondActivity*/
    public void click5(View v)
    {
    	Intent intent = new Intent();
    	intent.setAction("com.itheima.sa2");
//    	intent.setData(Uri.parse("heima2:qwe"));
//    	intent.setType("text/username");
//    	intent.setData(Uri.parse("heima2:qwe123"));
    	
    	intent.setDataAndType(Uri.parse("heima2:qwe123"), "text/username");
    	//系统会自动添加默认的category
    	intent.addCategory(Intent.CATEGORY_DEFAULT);
    	startActivity(intent);
    }
    /**显式跳转至浏览器*/
    public void click6(View v)
    {
    	Intent intent = new Intent();
    	intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
    	startActivity(intent);
    }
    /**隐式跳转至浏览器*/
    public void click7(View v)
    {
    	Intent intent = new Intent();
    	intent.setAction(Intent.ACTION_VIEW);
    	intent.setData(Uri.parse("http://www.baidu.com"));
    	startActivity(intent);
    }
}