package com.itheima.logcat;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;

public class MainActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("abc");
        System.err.println("def");
        Log.e("上miss", "谁能赐我一shi");
        Log.w("上miss", "谁能赐我一shi");
        Log.i("上miss", "谁能赐我一shi");
        Log.d("上miss", "谁能赐我一shi");
        Log.v("上miss", "谁能赐我一shi");
    }
}
