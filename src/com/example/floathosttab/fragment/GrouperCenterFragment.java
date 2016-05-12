package com.example.floathosttab.fragment;



import com.example.floathosttab.R;
import com.example.floathosttab.view.MyScrollView;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import android.widget.TextView;
/*
 * 班组长的通知中心fragment
 */
public class GrouperCenterFragment extends Fragment{
	/*
	 * �?��装的fragment
	 */
    private final static Class fragmentArray[] = {ReceivedNoticeFragment.class,SendedNoticeFragment.class,SendNoticeFragment.class};
    private final static String tabArray[] = {"已接收", "已发布", "发布"};
    private final static int tabDrawable[] = {R.drawable.tabhost_title_left,R.drawable.tabhost_title_center,R.drawable.tabhost_title_right};
    private FragmentTabHost tabHost;
    private Context mContext;
    private LayoutInflater inflater;
    private LinearLayout ll_tabhost;
  
    private  View contentView;
    private MyScrollView scrollview;
   @Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mContext = getActivity();
		inflater = LayoutInflater.from(mContext);
	}
   @Override
   public void onResume() {
	// TODO Auto-generated method stub
	   
	 
	  
	 super.onResume();
	  
  }
   @SuppressWarnings("deprecation")
@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
	    contentView = inflater.inflate(R.layout.grouper_notice_fragment, null);
	    ll_tabhost = (LinearLayout) contentView.findViewById(R.id.ll_tabhost);
	    Display d = ((Activity) mContext).getWindowManager().getDefaultDisplay();
	    scrollview = (MyScrollView) contentView.findViewById(R.id.scrollview);
	    LinearLayout ll_top = (LinearLayout) contentView.findViewById(R.id.ll_top);
//	    scrollview.setMaxScroll(600);
	    scrollview.setTopView(ll_top);
	    scrollview.setBottomView(ll_tabhost);
	   tabHost = (FragmentTabHost) contentView.findViewById(android.R.id.tabhost);
	   tabHost.setup(mContext, getChildFragmentManager(), R.id.realtabcontent);
	   initTabHost();
	  tabHost.setFocusable(false);
		return contentView;
	}
    private void initTabHost() {
    	for (int i=0;i<3;i++) {
    		TabSpec tabSpec = tabHost.newTabSpec(tabArray[i])
    				.setIndicator(getTabItemView(i));
        	// 将Tab按钮添加进Tab选项卡中
        	tabHost.addTab(tabSpec, fragmentArray[i], null);
    	}
    	TabWidget tabWidget = tabHost.getTabWidget();
//    	tabWidget.setPadding(StringUtils.dip2px(14, mContext), 0, StringUtils.dip2px(14, mContext), 0);
		tabWidget.setBackground(getResources().getDrawable(R.drawable.tabhostdrawable));
    	tabWidget.getChildAt(0).setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {   //咨询中心
				// TODO Auto-generated method stub
				tabHost.setCurrentTab(0);
			}
			
		});
		tabWidget.getChildAt(1).setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {   //订单中心
				// TODO Auto-generated method stub
				tabHost.setCurrentTab(1);
			}
			
		});
		tabWidget.getChildAt(2).setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {   //服务中心
				// TODO Auto-generated method stub
				tabHost.setCurrentTab(2);
			}
		});
		tabHost.setCurrentTab(0);
    	
   }
    private View getTabItemView(int i) {
    	View tabview = inflater.inflate(R.layout.tabhost_item, null);
    	TextView tv_lable = (TextView) tabview.findViewById(R.id.tv_lable);
    	tv_lable.setText(tabArray[i]);
    	tabview.setBackgroundResource(tabDrawable[i]);
    	ImageView icon = (ImageView) tabview.findViewById(R.id.icon);
    	/*if (i == 2) {
    		icon.setImageResource(R.drawable.icon_new);
    	} else {
    		icon.setVisibility(View.GONE);
    	}*/
    	return tabview;
    }
}
