package com.itheima.junit.test;

import com.itheima.junit.utils.Utils;

import android.test.AndroidTestCase;

public class TestCase extends AndroidTestCase {

	public void test(){
		int result = Utils.add(3, 5);
		//断言：用来检测实际值与期望值是否一致
		assertEquals(8, result);
	}
	
	public void test2(){
		Utils.chuyi(2, 1);
	}
}
