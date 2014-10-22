package com.example.weixin.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.AdapterContextMenuInfo;

import com.actionbarsherlock.app.SherlockListFragment;
import com.example.weixin.R;
import com.example.weixin.bean.WeiXin;
import com.example.weixin.manager.UniversalAdapter;
import com.example.weixin.manager.ViewHolder;

/**
 * 微信fragment
 * 
 * @author kongbei
 *
 */
public class FragmentWeixin extends SherlockListFragment {
	private static List<WeiXin> wxs = new ArrayList<WeiXin>();
	private UniversalAdapter<WeiXin> wxAdapter;
	private WeiXin wx = null; 

	public static FragmentWeixin newInstance(List<WeiXin> list) {
		FragmentWeixin fw = new FragmentWeixin();
		wxs = list;
		return fw;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_pager_list2, container, false);
		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		registerForContextMenu(getListView());//为上下文登记ListView

		wxAdapter = new UniversalAdapter<WeiXin>(getActivity(), wxs, R.layout.item_weixin) {
			@Override
			public void convert(ViewHolder vh, WeiXin item, int position) {
				if (item == null)
					return;

				if (vh == null)
					return;

				if (item.getImgUrl() == null) {
					vh.setImageResource(R.id.iv_head_portrait, R.drawable.ic_launcher);
				} else {
					vh.setImageByUrl(R.id.iv_head_portrait, item.getImgUrl());
				}

				vh.setText(R.id.tv_desc1, item.getText1());

				if (item.getText2().length() > 11) {
					vh.setText(R.id.tv_desc2, item.getText2().subSequence(0, 11).toString() + "...");
				} else {
					vh.setText(R.id.tv_desc2, item.getText2());
				}

				vh.setText(R.id.tv_time, item.getTime());
			}
		};

		setListAdapter(wxAdapter);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		getActivity().getMenuInflater().inflate(R.menu.weixin_menu, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item.getMenuInfo();
		final int position = menuInfo.position;
		if (wxs != null && wxs.size() > 0) {
			wx = wxs.get(position);
		}

		switch (item.getItemId()) {
		case R.id.menu_item_read:
			com.example.weixin.utils.T.showShort(getActivity(), "标为未读");
			return true;
		case R.id.menu_item_top:
			com.example.weixin.utils.T.showShort(getActivity(), "置顶");
			return true;
		case R.id.menu_item_delete:
			com.example.weixin.utils.T.showShort(getActivity(), "删除该聊天");
			return true;
		}
		return super.onContextItemSelected(item);
	}
}
