package com.itheima.ipdialer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

public class CallReceiver extends BroadcastReceiver
{
	@Override
	public void onReceive(Context context, Intent intent)
	{
		//在打电话广播中，会携带拨打的电话的号码，通过以下代码获取到
		String number = getResultData();
		if(number.startsWith("0"))
		{
			SharedPreferences sp = context.getSharedPreferences("ip", Context.MODE_PRIVATE);
			String ipNumber = sp.getString("ipNumber", "");
			number = ipNumber + number;
			setResultData(number);
			abortBroadcast();
			Log.e("0", number);
		}
	}
}