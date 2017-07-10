package com.itheima.zhbj52.fragment;

import com.itheima.zhbj52.R;

import android.view.View;

/**
 * 主页内容
 * 
 * @author Kevin
 * 
 */
public class ContentFragment extends BaseFragment {

	@Override
	public View initViews() {
		View view = View.inflate(mActivity, R.layout.fragment_content, null);
		return view;
	}

}
