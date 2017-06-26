package com.itheima.smslanjie;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsReceiver extends BroadcastReceiver
{
	@Override
	public void onReceive(Context context, Intent intent)
	{
		Bundle bundle = intent.getExtras();
		Object[] objects = (Object[]) bundle.get("pdus");
		for (Object object : objects)
		{
			//通过pdu来构造短信
			SmsMessage sms = SmsMessage.createFromPdu((byte[])object);
			Log.e("0", sms.getOriginatingAddress());
			if(sms.getOriginatingAddress().equals("15555215556"))
			{
				abortBroadcast();
				SmsManager.getDefault().sendTextMessage(sms.getOriginatingAddress(), null, "you are a good person", null, null);
			}
			Log.e("0", sms.getMessageBody());
		}
	}
}