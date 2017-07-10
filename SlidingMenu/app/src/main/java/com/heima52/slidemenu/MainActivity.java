package com.heima52.slidemenu;

import com.heima52.slidemenu.view.SlideMenu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private ImageView btn_back;
	private SlideMenu slideMenu;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		btn_back = (ImageView) findViewById(R.id.btn_back);
		slideMenu = (SlideMenu) findViewById(R.id.slideMenu);
		
		btn_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				slideMenu.switchMenu();
			}
		});
	}


}
