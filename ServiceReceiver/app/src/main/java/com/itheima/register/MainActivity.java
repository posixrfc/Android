package com.itheima.register;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    private Intent intent;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(this, RegisterService.class);
    }


    public void start(View v){
    	startService(intent);
    }
    public void stop(View v){
    	stopService(intent);
    }
    
}
