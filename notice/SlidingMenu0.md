## SlidingMenu(侧边栏效果) ##

最火的Android开源项目
http://www.csdn.net/article/2013-05-03/2815127-Android-open-source-projects

> 使用步骤

- 1. 引入SlidingMenu的库文件
- 2. Activity继承SlidingFragmentActivity
- 3. 将onCreate方法改为public
- 调用api

			setBehindContentView(R.layout.left_menu);// 设置侧边栏布局

			SlidingMenu slidingMenu = getSlidingMenu();// 获取侧边栏对象
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);// 设置全屏触摸
	
			slidingMenu.setSecondaryMenu(R.layout.right_menu);// 设置右侧边栏
			slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);// 设置展现模式
	
			slidingMenu.setBehindOffset(100);// 设置预留屏幕的宽度

## Json ##

{"name":"zhangsan", "age":18, "books":[{"name":"金瓶梅","price":10}, {"name":"红楼梦","price":20}]}


## Gson ##

google + json

谷歌提供的开源的解析json的框架

Gson gson = new Gson();
NewsData data = gson.fromJson(result, NewsData.class);

## ViewPagerIndicator ##

ViewPager指针项目，在使用ViewPager的时候能够指示ViewPager所在的位置，就像Google Play中切换的效果一样，还能使用在应用初始化的介绍页面

1. 引入ViewPagerIndicator库
2. 编写布局文件

		 <com.viewpagerindicator.TabPageIndicator
        android:id="@+id/indicator"
        android:layout_width="fill_parent"
        android:layout_height="wrap_content" />

3. mIndicator.setViewPager(mViewPager);//将viewpager和mIndicator关联起来,必须在viewpager设置完adapter后才能调用

4. 重写PagerAdapter方法,返回页面标题

		/**
		 * 重写此方法,返回页面标题,用于viewpagerIndicator的页签显示
		 */
		@Override
		public CharSequence getPageTitle(int position) {
			return mNewsTabData.get(position).title;
		}

5. 自定义样式修改

## BitmapUtils ##

- BitmapFun
- ImageDownloader

## ShareSDK ##