package com.itheima.lifecycle;

import com.itheima.hengshu.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class SecondActivity extends Activity {

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        System.out.println("second：create");
    }

    @Override
    protected void onStart() {
    	super.onStart();
        Log.e("0", "second：start");
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
        Log.e("0", "second：resume");
    };
    
    @Override
    protected void onPause() {
    	super.onPause();
        Log.e("0", "second：pause");
    }
    
    @Override
    protected void onStop() {
    	super.onStop();
        Log.e("0", "second：stop");
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
        Log.e("0", "second：destroy");
    }
    
    @Override
    protected void onRestart() {
    	super.onRestart();
        Log.e("0", "second:restart");
    }
}