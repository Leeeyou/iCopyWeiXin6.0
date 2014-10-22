package com.example.weixin.manager;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * 通用Adapter
 *  
 * @author liyou
 *
 * @param <T>
 */
public abstract class UniversalAdapter<T> extends BaseAdapter {
	protected final Context context;

	protected final List<T> mData; 

	protected int layoutId;

	public UniversalAdapter(Context context, List<T> data, int layoutId) {
		super();
		this.context = context;
		this.mData = data;
		this.layoutId = layoutId;
	}

	@Override
	public int getCount() {
		if (mData != null)
			return mData.size();
		return 0;
	}

	@Override
	public T getItem(int position) {
		if (position < mData.size())
			return mData.get(position);
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh = ViewHolder.get(context, convertView, parent, layoutId, position);
		convert(vh, getItem(position), position);
		return vh.getConvertView();
	}

	public abstract void convert(ViewHolder vh, T item, int position);

}
