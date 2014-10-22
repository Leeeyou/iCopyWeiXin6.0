package com.example.weixin;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.LayoutParams;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.example.weixin.utils.KeyBoardUtils;
import com.example.weixin.utils.T;

public class ActivitySearch extends SherlockFragmentActivity implements SearchView.OnQueryTextListener {

	@InjectView(R.id.rl_search)
	RelativeLayout rl_search;
	SearchView mSearchView;
	LinearLayout ll_back;
	LinearLayout ll_voice;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		ButterKnife.inject(this);

		rl_search = (RelativeLayout) findViewById(R.id.rl_search);
		rl_search.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		// 自定义标题栏  
		getSupportActionBar().setDisplayShowHomeEnabled(false);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
		getSupportActionBar().setDisplayShowCustomEnabled(true);
		LayoutInflater mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View mTitleView = mInflater.inflate(R.layout.custom_action_bar_layout, null);
		getSupportActionBar().setCustomView(mTitleView, new ActionBar.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

		mSearchView = (SearchView) findViewById(R.id.search_view);

		mSearchView.setBackgroundColor(Color.TRANSPARENT);
		mSearchView.setOnQueryTextListener(this);
		mSearchView.setSubmitButtonEnabled(false);

		ll_back = (LinearLayout) findViewById(R.id.ll_back);
		ll_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		ll_voice = (LinearLayout) findViewById(R.id.ll_voice);
		ll_voice.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				T.showShort(ActivitySearch.this, "语音");
			}
		});

		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(mSearchView, InputMethodManager.RESULT_SHOWN);
		imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
	}

	@Override
	public boolean onQueryTextChange(String newText) {
		if (TextUtils.isEmpty(newText)) {
			T.showShort(this, "关键字被清空");
		} else {
			T.showShort(this, newText);
		}
		return false;
	}

	@Override
	public boolean onQueryTextSubmit(String text) {
		if (TextUtils.isEmpty(text))
			return false;
		T.showShort(this, " 执行搜索   --> 关键字：" + text);
		return true;
	}

	@Override
	protected void onPause() {
		super.onPause();
		KeyBoardUtils.closeKeybord(rl_search, this);
		mSearchView.setFocusable(false);
	}

}
