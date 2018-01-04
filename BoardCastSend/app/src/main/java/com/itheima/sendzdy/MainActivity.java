package com.itheima.sendzdy;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void click(View v)
    {
    	Intent intent = new Intent();
    	intent.setAction("com.itheima.zdy");
    	sendBroadcast(intent);
    }
}