package com.itheima.preference;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;

public class MainActivity extends PreferenceActivity
{
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
		
		//通过资源id指定要把哪个preference的内容显示至界面
		addPreferencesFromResource(R.xml.pref);
		Preference pf = findPreference("pre");
		pf.setSummary("这是修改过后的摘要：小志和b哥不能说的故事");
	}


}
