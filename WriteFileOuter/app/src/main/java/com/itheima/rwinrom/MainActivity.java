package com.itheima.rwinrom;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import com.itheima.rwinsd.R;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity
{
    private EditText et_name;
	private EditText et_pass;
	@Override
    protected void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_name = (EditText) findViewById(R.id.et_name);
    	et_pass = (EditText) findViewById(R.id.et_pass);
        readAccount();
    }
    public void readAccount()
	{
    	if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
	    	return;
    	}
		File file = new File("sdcard/info.txt");
		if(!file.exists()) {
			return;
		}
		try {
			FileInputStream fis = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String text = br.readLine();
			String[] s = text.split("##");
			et_name.setText(s[0]);
			et_pass.setText(s[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    public void login(View v)
	{
    	String name = et_name.getText().toString();
    	String pass = et_pass.getText().toString();
    	CheckBox cb = (CheckBox) findViewById(R.id.cb);
		Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    	if(!cb.isChecked()) {
    		return;
    	}
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
		{
			File file = new File(Environment.getExternalStorageDirectory(), "info.txt");
			FileOutputStream fos;
			try {
				fos = new FileOutputStream(file);
				fos.write((name + "##" + pass).getBytes());
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else
		{
			Toast.makeText(this, "sd卡不可用哟亲么么哒", Toast.LENGTH_SHORT).show();
		}
    }
}