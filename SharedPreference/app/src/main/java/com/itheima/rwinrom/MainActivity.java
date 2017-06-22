package com.itheima.rwinrom;

import com.itheima.sharedpreference.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
	    SharedPreferences sp = getSharedPreferences("info", MODE_PRIVATE);
	    String name = sp.getString("name", "");
	    String pass = sp.getString("pass", "");
		et_name.setText(name);
		et_pass.setText(pass);
    }
    public void login(View v)
	{
    	String name = et_name.getText().toString();
    	String pass = et_pass.getText().toString();
    	CheckBox cb = (CheckBox) findViewById(R.id.cb);
    	if(cb.isChecked())
    	{
    		SharedPreferences sp = getSharedPreferences("info", MODE_PRIVATE);
    		Editor ed = sp.edit();
    		ed.putString("name", name);
    		ed.putString("pass", pass);
    		ed.commit();
    	}
    	Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }
}