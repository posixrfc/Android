package com.itheima.lesuo;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public void onBackPressed() {
//    	super.onBackPressed();
        Log.e("0", "onBackPressed");
    }
    
}
