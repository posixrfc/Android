package com.itheima.lifecycle;

import android.app.Activity;
import android.os.Bundle;

public class SecondActivity extends Activity
{
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        System.out.println("second：create");
    }

    @Override
    protected void onStart() {
    	super.onStart();
    	System.out.println("second：start");
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	System.out.println("second：resume");
    };
    
    @Override
    protected void onPause() {
    	super.onPause();
    	System.out.println("second：pause");
    }
    
    @Override
    protected void onStop() {
    	super.onStop();
    	System.out.println("second：stop");
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	System.out.println("second：destroy");
    }
    
    @Override
    protected void onRestart() {
    	super.onRestart();
    	System.out.println("second:restart");
    }
}
