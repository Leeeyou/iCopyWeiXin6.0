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

package com.example.weixin;

import android.content.Intent;
import android.graphics.Color;
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
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.app.SherlockListFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class FragmentPagerSupport extends SherlockFragmentActivity {
	static final int NUM_ITEMS = 4;

	MyAdapter mAdapter;

	ViewPager mPager;

	String mCurFilter;

	private LinearLayout ll_weixin;
	private LinearLayout ll_contacts;
	private LinearLayout ll_found;
	private LinearLayout ll_own;

	private ImageView goto_weixin;
	private ImageView goto_contacts;
	private ImageView goto_found;
	private ImageView goto_own;

	private TextView tv_weixin;
	private TextView tv_contacts;
	private TextView tv_found;
	private TextView tv_own;

	private TextView[] tvList = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(SampleList.THEME); //Used for theme switching in samples
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_pager);

		mAdapter = new MyAdapter(getSupportFragmentManager());

		mPager = (ViewPager) findViewById(R.id.pager);
		mPager.setAdapter(mAdapter);
		mPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				setDescColor(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});

		goto_weixin = (ImageView) findViewById(R.id.goto_weixin);
		goto_contacts = (ImageView) findViewById(R.id.goto_contacts);
		goto_found = (ImageView) findViewById(R.id.goto_found);
		goto_own = (ImageView) findViewById(R.id.goto_own);

		tv_weixin = (TextView) findViewById(R.id.tv_weixin);
		tv_contacts = (TextView) findViewById(R.id.tv_contacts);
		tv_found = (TextView) findViewById(R.id.tv_found);
		tv_own = (TextView) findViewById(R.id.tv_own);

		tvList = new TextView[] { tv_weixin, tv_contacts, tv_found, tv_own };

		ll_weixin = (LinearLayout) findViewById(R.id.ll_weixin);
		ll_contacts = (LinearLayout) findViewById(R.id.ll_contacts);
		ll_found = (LinearLayout) findViewById(R.id.ll_found);
		ll_own = (LinearLayout) findViewById(R.id.ll_own);

		setDescColor(0);

		ll_weixin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mPager.setCurrentItem(0);
				setDescColor(0);
			}
		});
		ll_contacts.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				mPager.setCurrentItem(1);
				setDescColor(1);
			}
		});
		ll_found.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				mPager.setCurrentItem(2);
				setDescColor(2);
			}
		});
		ll_own.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				mPager.setCurrentItem(3);
				setDescColor(3);
			}
		});

		getSupportActionBar().setDisplayShowHomeEnabled(false);

	}

	protected void setDescColor(int index) {
		for (int i = 0; i < tvList.length; i++) {
			TextView textView = tvList[i];
			if (textView == null) {
				break;
			}
			if (i == index) {
				textView.setTextColor(Color.GREEN);
			} else {
				textView.setTextColor(Color.GRAY);
			}
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
			return ArrayListFragment.newInstance(position);
		}
	}

	public static class ArrayListFragment extends SherlockListFragment {
		int mNum;

		/**
		 * Create a new instance of CountingFragment, providing "num"
		 * as an argument.
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
		//Used to put dark icons on light action bar
		boolean isLight = SampleList.THEME == R.style.Theme_Sherlock_Light;

		menu.add("Search").setIcon(isLight ? android.R.drawable.ic_menu_search : R.drawable.ic_search).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
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
}
