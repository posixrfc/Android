package com.guosen.popupwindow;

import android.graphics.drawable.ColorDrawable;
import android.widget.PopupWindow;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;

public class TestPopwindow2 extends PopupWindow
{
    private View mRootView;
    LayoutInflater mInflater;

    public TestPopwindow2(Activity context)
    {
        super(context);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mRootView = mInflater.inflate(R.layout.test_popwindow_2, null);
        setContentView(mRootView);

        this.setWidth(LayoutParams.WRAP_CONTENT);
        this.setHeight(LayoutParams.WRAP_CONTENT);

        setTouchable(true);
        setOutsideTouchable(true);
        setFocusable(true);
        setBackgroundDrawable(new ColorDrawable(0XFF0000FF));
        update();

        getContentView().setFocusableInTouchMode(true);
        getContentView().setFocusable(true);
        setAnimationStyle(R.style.AppTheme);
    }
}
