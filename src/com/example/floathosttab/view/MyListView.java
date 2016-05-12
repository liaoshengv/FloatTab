package com.example.floathosttab.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.ListView;
import android.widget.ScrollView;

public class MyListView extends ListView {
	public MyListView(Context context){
		super(context);
	}
	public MyListView(Context context, AttributeSet attrs){
		super(context, attrs);
		init();
	}
	public MyListView(Context context, AttributeSet attrs,int defStyleAttr){
		super(context, attrs, defStyleAttr);
		init();
	}
	public MyListView(Context context, AttributeSet attrs, int defStyleAttr,
			int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		// TODO Auto-generated constructor stub
		init();
	}
	private void init() {
		this.setFocusable(false);
	}
	float last_y;
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
    	// TODO Auto-generated method stub
    
    	float temp_y = last_y;
    	last_y  = ev.getY();
    	
    	if (ev.getAction() == MotionEvent.ACTION_MOVE) {
    		if (last_y > temp_y && getFirstVisiblePosition() == 0 && getChildAt(0).getY() == 0) {
    			  
    			
    			 ViewParent parent = (ViewParent) getParent();
    			 while (parent != null) {
    				 if (parent instanceof MyScrollView) {
    					 ((MyScrollView)parent).requestInterceptTouchEvent(false,ev);
    					 break;
    				 }
    				 parent = parent.getParent();
    				 Log.d("MyListView", "onTouchEvent:" + ev.getAction() +"--" +getChildAt(0).getY() +"--"+getFirstVisiblePosition());
    			 }
    			
//    			 return false;
    		} else if(last_y < temp_y && getLastVisiblePosition() == getCount() - 1){

   			 ViewParent parent = (ViewParent) getParent();
   			 while (parent != null) {
   				 if (parent instanceof MyScrollView) {
   					 ((MyScrollView)parent).requestInterceptTouchEvent(false,ev);
   					 break;
   				  }
   				 parent = parent.getParent();
   				 Log.d("MyListView", "onTouchEvent:" + ev.getAction() +"--" +getChildAt(0).getY() +"--"+getFirstVisiblePosition());
   			  }
    		}
    	}
    	return super.onTouchEvent(ev);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
    	// TODO Auto-generated method stub
    	 Log.d("MyListView", "dispatchTouchEvent");
    	if(getFirstVisiblePosition() != 0 || getChildAt(0).getY() != 0) {
//    		 ((MyScrollView)parent).requestInterceptTouchEvent(false,ev);
    		requestDisallowInterceptTouchEvent(true);
    	}
    	return super.dispatchTouchEvent(ev);
    	
    }
}
