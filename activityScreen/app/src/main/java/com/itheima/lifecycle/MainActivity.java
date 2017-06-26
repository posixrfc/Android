package com.itheima.lifecycle;

import com.itheima.hengshu.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity
{
	int blood;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Log.e("0", "main：create");
        blood = 100;
    }

    @Override
    protected void onStart() {
    	super.onStart();
        Log.e("0", "main：start");
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
        Log.e("0", "main：resume");
    };
    
    @Override
    protected void onPause() {
    	super.onPause();
        Log.e("0", "main：pause");
    }
    
    @Override
    protected void onStop() {
    	super.onStop();
        Log.e("0", "main：stop");
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
        Log.e("0", "main：destroy");
    };
    
    @Override
    protected void onRestart() {
    	super.onRestart();
        Log.e("0", "main:restart");
    }
    public void click(View v)
    {
    	Intent intent = new Intent(this, SecondActivity.class);
    	startActivity(intent);
    }
}