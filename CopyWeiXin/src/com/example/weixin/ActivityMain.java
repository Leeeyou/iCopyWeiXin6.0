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

import java.util.ArrayList;
import java.util.List;

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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.app.SherlockListFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.example.weixin.bean.Cheeses;
import com.example.weixin.bean.Portrait;
import com.example.weixin.bean.Talks;
import com.example.weixin.bean.WeiXin;
import com.example.weixin.fragment.FragmentWeixin;
import com.example.weixin.utils.T;

/**
 * 主界面
 * @author xzzz
 *
 */
public class ActivityMain extends SherlockFragmentActivity {
	static final int NUM_ITEMS = 4;

	MyAdapter mAdapter;

	@InjectView(R.id.pager)
	ViewPager mPager;

	String mCurFilter;

	@InjectView(R.id.ll_weixin)
	LinearLayout ll_weixin;
	@InjectView(R.id.ll_contacts)
	LinearLayout ll_contacts;
	@InjectView(R.id.ll_found)
	LinearLayout ll_found;
	@InjectView(R.id.ll_own)
	LinearLayout ll_own;

	//	private ImageView goto_weixin;
	//	private ImageView goto_contacts;
	//	private ImageView goto_found;
	//	private ImageView goto_own;

	@InjectView(R.id.tv_weixin)
	TextView tv_weixin;
	@InjectView(R.id.tv_contacts)
	TextView tv_contacts;
	@InjectView(R.id.tv_found)
	TextView tv_found;
	@InjectView(R.id.tv_own)
	TextView tv_own;

	private TextView[] tvList = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_pager);
		ButterKnife.inject(this);

		mAdapter = new MyAdapter(getSupportFragmentManager());
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

		tvList = new TextView[] { tv_weixin, tv_contacts, tv_found, tv_own };

		setDescColor(0);

		getSupportActionBar().setDisplayShowHomeEnabled(false);
	}

	@OnClick(R.id.ll_weixin)
	public void ClickWeiXin() {
		mPager.setCurrentItem(0);
		setDescColor(0);
	}

	@OnClick(R.id.ll_contacts)
	public void ClickContacts() {
		mPager.setCurrentItem(1);
		setDescColor(1);
	}

	@OnClick(R.id.ll_found)
	public void ClickFound() {
		mPager.setCurrentItem(2);
		setDescColor(2);
	}

	@OnClick(R.id.ll_own)
	public void ClickOwn() {
		mPager.setCurrentItem(3);
		setDescColor(3);
	}

	/**
	 * 选中时颜色
	 * @param index
	 */
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
}
