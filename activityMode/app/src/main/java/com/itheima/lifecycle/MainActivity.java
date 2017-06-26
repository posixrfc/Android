package com.itheima.lifecycle;

import com.itheima.launchmode.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("0", "main：create");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("0", "main：onDestroy");
    }
    public void click1(View v)
    {
    	Intent intent = new Intent(this, MainActivity.class);
    	startActivity(intent);
    }
    public void click2(View v)
    {
    	Intent intent = new Intent(this, SecondActivity.class);
    	startActivity(intent);
    }
}