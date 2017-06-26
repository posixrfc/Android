package com.itheima.senddata;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void click(View v)
    {
    	Intent intent = new Intent(this, SecondActivity.class);
    	intent.putExtra("malename", "李志");
    	intent.putExtra("femalename", "芙蓉姐姐");
    	
//    	Bundle bundle = new Bundle();
//    	bundle.putString("malename", "李志");
//    	bundle.putString("femalename", "芙蓉姐姐");
//    	intent.putExtras(bundle);
    	startActivity(intent);
    }
    
}
