package com.guosen.popupwindow;

import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener, OnDismissListener
{
    private TestPopwindow2 mTestPopwindow2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTestPopwindow2 = new TestPopwindow2(this);
        mTestPopwindow2.setOnDismissListener(this);
        Button buttonTest2 = (Button) findViewById(R.id.buttonTest2);
        buttonTest2.setOnClickListener(this);
    }

    public void OnclickTestListener(View view)
    {
        switch (view.getId())
        {
            case R.id.layoutSeclect1:
                Toast.makeText(this, "系统热门方案", Toast.LENGTH_SHORT).show();
                break;
            case R.id.layoutSeclect2:
                Toast.makeText(this, "个人热门方案", Toast.LENGTH_SHORT).show();
        }
        if (mTestPopwindow2 != null)
            mTestPopwindow2.dismiss();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() != R.id.buttonTest2)
            return;
        if (mTestPopwindow2 == null)
            return;
        int[] location = new int[2];

        v = findViewById(R.id.buttonTest2);
        if (v != null)
            v.getLocationOnScreen(location);
        mTestPopwindow2.setAnimationStyle(R.style.AppTheme);
        mTestPopwindow2.showAtLocation(v, Gravity.TOP | Gravity.LEFT, location[0] - v.getWidth(), location[1] + v.getHeight());
    }

    @Override
    public void onDismiss()
    {
    }
}