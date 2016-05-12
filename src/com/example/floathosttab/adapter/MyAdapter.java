package com.example.floathosttab.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
	private Context context;
	private int count;
    public MyAdapter(Context context,int count) {
    	this.context = context;
    	this.count = count;
    }
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return count;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			TextView textview = new TextView(context);
		    textview.setGravity(Gravity.CENTER_VERTICAL);
		    textview.setPadding(20, 20, 0, 20);
		    textview.setTextSize(20);
		    convertView = textview;
		}
		((TextView)convertView).setText("item" + (position+1));
		return convertView;
	}

}
