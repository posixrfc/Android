package com.itheima.clickevent;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt1 = (Button) findViewById(R.id.bt1);
        bt1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				System.out.println(((Button) v).getText());
			}
		});
        Button bt2 = (Button) findViewById(R.id.bt2);
        bt2.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		System.out.println(((Button) v).getText());
	}

	public void getScore(View v)
	{
		System.out.println(((Button) v).getText());
		switch (v.getId())
		{
		case R.id.wangzhe:
			System.out.println("下辈子吧");
			break;

		case R.id.diamond:
			System.out.println("凑合凑合");
			break;

		case R.id.master:
			System.out.println("想想就好");
		}
	}
}