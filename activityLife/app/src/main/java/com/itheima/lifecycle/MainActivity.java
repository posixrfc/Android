package com.itheima.lifecycle;

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
        System.out.println("main：create");
    }

    @Override
    protected void onStart() {
    	super.onStart();
    	System.out.println("main：start");
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	System.out.println("main：resume");
    };
    
    @Override
    protected void onPause() {
    	super.onPause();
    	System.out.println("main：pause");
    }
    
    @Override
    protected void onStop() {
    	super.onStop();
    	System.out.println("main：stop");
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	System.out.println("main：destroy");
    };
    
    @Override
    protected void onRestart() {
    	super.onRestart();
    	System.out.println("main:restart");
    }
    
    public void click(View v){
    	Intent intent = new Intent(this, SecondActivity.class);
    	startActivity(intent);
    }
    
}
