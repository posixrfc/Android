package com.itheima.local;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ShengZF extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String text = getResultData();
		System.out.println("省政府收到文件：" + text);
		setResultData("每人发80斤大米");
	}

}
