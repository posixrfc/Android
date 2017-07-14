package com.guosen.popupwindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener, OnDismissListener
{
    private PopupWindow popwindow;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LayoutInflater mInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mRootView = mInflater.inflate(R.layout.pop_cnt, null);
        mRootView.setFocusableInTouchMode(true);
        mRootView.setFocusable(true);

        popwindow = new PopupWindow(this);
        popwindow.setContentView(mRootView);
        popwindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popwindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popwindow.setFocusable(true);
        popwindow.setTouchable(true);
        popwindow.setOutsideTouchable(true);
        popwindow.setBackgroundDrawable(new ColorDrawable(0XFF0000FF));
        popwindow.update();
        popwindow.setOnDismissListener(this);

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
        popwindow.dismiss();
    }

    @Override
    public void onClick(View v)
    {
        int[] location = new int[2];
        v.getLocationOnScreen(location);
//        popwindow.showAtLocation(v, Gravity.END | Gravity.BOTTOM, location[0], location[1]);
        popwindow.showAsDropDown(v, 0, 0);
    }

    @Override
    public void onDismiss()
    {
        Log.e("onDismiss", "onDismiss");
    }
}