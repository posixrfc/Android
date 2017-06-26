package com.itheima.center;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
    	Intent intent = new Intent();
    	intent.setAction("com.itheima.fdm");
    	sendOrderedBroadcast(intent, null, new MyReceiver(), null, 0, "每人发100斤大米", null);
    }
    class MyReceiver extends BroadcastReceiver
    {
		@Override
		public void onReceive(Context context, Intent intent) {
			String text = getResultData();
			Log.e("0", "反贪局收到文件：" + text);
		}
    }
}