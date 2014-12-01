/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.leeyou.weixin6;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.app.SherlockListFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.leeyou.weixin6.bean.Cheeses;
import com.leeyou.weixin6.bean.Portrait;
import com.leeyou.weixin6.bean.Talks;
import com.leeyou.weixin6.bean.WeiXin;
import com.leeyou.weixin6.fragment.FragmentWeixin;
import com.leeyou.weixin6.ui.ChangeColorIconWithTextView;
import com.leeyou.weixin6.utils.T;

/**
 * 主界面
 *
 */
public class ActivityMain extends SherlockFragmentActivity implements OnClickListener {
	static final int NUM_ITEMS = 4;

	MyAdapter mAdapter;

	@InjectView(R.id.id_viewpager)
	ViewPager mViewPager;

	String mCurFilter;

	@InjectView(R.id.id_indicator_weixin)
	private ChangeColorIconWithTextView mWeixin;
	@InjectView(R.id.id_indicator_linkman)
	private ChangeColorIconWithTextView mLinkman;
	@InjectView(R.id.id_indicator_found)
	private ChangeColorIconWithTextView mFound;
	@InjectView(R.id.id_indicator_own)
	private ChangeColorIconWithTextView mOwn;

	private List<ChangeColorIconWithTextView> mTabIndicator = new ArrayList<ChangeColorIconWithTextView>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.inject(this);

		T.showShort(this, "mViewPager == null --> " + (mViewPager == null));

		setOverflowShowingAlways();

		initDatas();

		getSupportActionBar().setDisplayShowHomeEnabled(false);
	}

	private void initDatas() {
		mAdapter = new MyAdapter(getSupportFragmentManager());
		mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
		mViewPager.setAdapter(mAdapter);
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
			}

			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				if (positionOffset > 0) {
					ChangeColorIconWithTextView left = mTabIndicator.get(position);
					ChangeColorIconWithTextView right = mTabIndicator.get(position + 1);

					left.setIconAlpha(1 - positionOffset);
					right.setIconAlpha(positionOffset);
				}
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});

		initTabIndicator();
	}

	private void initTabIndicator() {
		mWeixin = (ChangeColorIconWithTextView) findViewById(R.id.id_indicator_weixin);
		mLinkman = (ChangeColorIconWithTextView) findViewById(R.id.id_indicator_linkman);
		mFound = (ChangeColorIconWithTextView) findViewById(R.id.id_indicator_found);
		mOwn = (ChangeColorIconWithTextView) findViewById(R.id.id_indicator_own);

		mTabIndicator.add(mWeixin);
		mTabIndicator.add(mLinkman);
		mTabIndicator.add(mFound);
		mTabIndicator.add(mOwn);

		mWeixin.setOnClickListener(this);
		mLinkman.setOnClickListener(this);
		mFound.setOnClickListener(this);
		mOwn.setOnClickListener(this);

		mWeixin.setIconAlpha(1.0f);
	}

	@Override
	public void onClick(View v) {
		resetOtherTabs();
		switch (v.getId()) {
		case R.id.id_indicator_weixin:
			mTabIndicator.get(0).setIconAlpha(1.0f);
			mViewPager.setCurrentItem(0, false);
			break;
		case R.id.id_indicator_linkman:
			mTabIndicator.get(1).setIconAlpha(1.0f);
			mViewPager.setCurrentItem(1, false);
			break;
		case R.id.id_indicator_found:
			mTabIndicator.get(2).setIconAlpha(1.0f);
			mViewPager.setCurrentItem(2, false);
			break;
		case R.id.id_indicator_own:
			mTabIndicator.get(3).setIconAlpha(1.0f);
			mViewPager.setCurrentItem(3, false);
			break;
		}
	}

	/**
	 * 重置其他的Tab
	 */
	private void resetOtherTabs() {
		for (int i = 0; i < mTabIndicator.size(); i++) {
			mTabIndicator.get(i).setIconAlpha(0);
		}
	}

	public static class MyAdapter extends FragmentPagerAdapter {
		public MyAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public int getCount() {
			return NUM_ITEMS;
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
			case 0:
				List<WeiXin> list = new ArrayList<WeiXin>();
				for (int i = 1; i < 101; i++) {
					WeiXin wx = new WeiXin();
					wx.setImgUrl(Portrait.sPortraitStrings[i % 4]);
					wx.setText1(Cheeses.sCheeseStrings[i % 100]);
					wx.setText2(Talks.sTalkStrings[i % 22]);
					wx.setTime("晚上18:39");
					list.add(wx);
				}
				return FragmentWeixin.newInstance(list);
			default:
				return ArrayListFragment.newInstance(position);
			}
		}
	}

	public static class ArrayListFragment extends SherlockListFragment {
		int mNum;

		/**
		 * Create a new instance of CountingFragment, 
		 * providing "num" as an argument.
		 */
		static ArrayListFragment newInstance(int num) {
			ArrayListFragment f = new ArrayListFragment();

			// Supply num input as an argument.
			Bundle args = new Bundle();
			args.putInt("num", num);
			f.setArguments(args);

			return f;
		}

		/**
		 * When creating, retrieve this instance's number from its arguments.
		 */
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			mNum = getArguments() != null ? getArguments().getInt("num") : 1;
		}

		/**
		 * The Fragment's UI is just a simple text view showing its
		 * instance number.
		 */
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View v = inflater.inflate(R.layout.fragment_pager_list, container, false);
			View tv = v.findViewById(R.id.text);
			String s = "";
			switch (mNum) {
			case 0:
				s = "微信";
				break;
			case 1:
				s = "通讯录";
				break;
			case 2:
				s = "发现";
				break;
			case 3:
				s = "我";
				break;
			}
			((TextView) tv).setText(s);
			return v;
		}

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);
			setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, Cheeses.sCheeseStrings));
		}

		@Override
		public void onListItemClick(ListView l, View v, int position, long id) {
			Log.i("FragmentList", "Item clicked: " + id);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add("Search").setIcon(android.R.drawable.ic_menu_search).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		menu.addSubMenu("发起群聊");
		menu.addSubMenu("添加朋友");
		menu.addSubMenu("扫一扫");
		menu.addSubMenu("意见反馈");

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		CharSequence name = item.getTitle();
		if (name.equals("Search")) {
			Intent i = new Intent(this, ActivitySearch.class);
			startActivity(i);
		}
		if (name.equals("发起群聊")) {
			T.showShort(this, "发起群聊");
		}
		if (name.equals("添加朋友")) {
			T.showShort(this, "添加朋友");
		}
		if (name.equals("扫一扫")) {
			T.showShort(this, "扫一扫");
		}
		if (name.equals("意见反馈")) {
			T.showShort(this, "意见反馈");
		}
		return super.onOptionsItemSelected(item);
	}

	private void setOverflowShowingAlways() {
		try {
			// true if a permanent menu key is present, false otherwise.
			ViewConfiguration config = ViewConfiguration.get(this);
			Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
			menuKeyField.setAccessible(true);
			menuKeyField.setBoolean(config, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
