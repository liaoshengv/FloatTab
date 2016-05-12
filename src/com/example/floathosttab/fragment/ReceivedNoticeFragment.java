package com.example.floathosttab.fragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;





import com.example.floathosttab.R;
import com.example.floathosttab.adapter.MyAdapter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
/*
 * 已接收的通知
 */
public class ReceivedNoticeFragment extends Fragment implements OnItemClickListener,OnClickListener{
	private ListView lv_list;
	private MyAdapter adapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}
   @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
	       View contentView =  inflater.inflate(R.layout.notice_list, null);
	       lv_list = (ListView) contentView.findViewById(R.id.lv_list);
	       lv_list.setOnItemClickListener(this);
	       adapter = new MyAdapter(getContext(), 20);
	       lv_list.setAdapter(adapter);
		  return contentView;
   }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		
	}
}
