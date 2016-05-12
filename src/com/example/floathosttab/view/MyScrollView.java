package com.example.floathosttab.view;


import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ScrollView;

public class MyScrollView extends ScrollView {
	private int maxY;
	private int mMaximumVelocity, mMinimumVelocity;
   private View topView;
   private View bottomView;
   private VelocityTracker tracker;
   private int mPointerId;
    public MyScrollView(Context context){
	   super(context);
		init();
    }
	public MyScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
		// TODO Auto-generated constructor stub
	}
  
	public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr,
			int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init();
		// TODO Auto-generated constructor stub
	}

   private void init() {
	   final ViewConfiguration configuration = ViewConfiguration.get(getContext());
       mMaximumVelocity = configuration.getScaledMaximumFlingVelocity();
       mMinimumVelocity = configuration.getScaledMinimumFlingVelocity();
   }

  
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		float temp_y = last_y;
		last_y = ev.getY();
		  if (ev.getAction() == MotionEvent.ACTION_MOVE && flag) {
			  Log.d("MyScrollView", "onTouchEvent:" + ev.getAction() +";"+ev.getY() + ";" + getScrollY() +";" + temp_y);
			  initVelocityTracker(); 
			  tracker.addMovement(ev);
			  smoothScrollTo(0, (int)(getScrollY() - ev.getY() + temp_y));
		      return true;
		   } else if (ev.getAction() == MotionEvent.ACTION_UP && flag) {
			   initVelocityTracker(); 
			   tracker.addMovement(ev);
			   tracker.computeCurrentVelocity(1000, mMaximumVelocity);
 			   int velocity_y = (int) tracker.getYVelocity(mPointerId);
			   Log.d(MyScrollView.class.getSimpleName(), "velocity_y:" + velocity_y);
			   fling(-velocity_y);
			   flag = false;
			   releaseVelocityTracker();
			   return true;
		   }  else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
		   } else if(ev.getAction() == MotionEvent.ACTION_CANCEL && flag) {
			   flag = false;
			   releaseVelocityTracker();
			   return true;
			   
		   }
		return super.onTouchEvent(ev);
	}
	private void initVelocityTracker() {
		if (null == tracker) {
			tracker = VelocityTracker.obtain();
		}
	}
	private void releaseVelocityTracker() {
		if(null != tracker) {  
			tracker.clear();  
			tracker.recycle();  
			tracker = null;  
        }  
	}
   @Override
   public boolean dispatchTouchEvent(MotionEvent ev) {
	// TODO Auto-generated method stu
	 return super.dispatchTouchEvent(ev);
   }
   @Override
   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
	    
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

	    final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (heightMode == MeasureSpec.UNSPECIFIED) {
            return;
        }
         maxY = topView.getMeasuredHeight();
         android.view.ViewGroup.LayoutParams params = bottomView.getLayoutParams();
         if (params.height != getMeasuredHeight() - getPaddingBottom() - getPaddingTop()) {
        	  params.height = getMeasuredHeight() - getPaddingBottom() - getPaddingTop() ;
         }
		
   }

   public void setTopView(View topView) {
	   this.topView = topView;
   }
   public void setBottomView(View bottomView) {
	   this.bottomView = bottomView;
   }
  
   float last_y;
   float orignal_y;
   public boolean onInterceptTouchEvent(MotionEvent ev){
	   Log.d("MyScrollView", "onInterceptTouchEvent:" + ev.getAction());
	   float temp_y = last_y;
	   last_y = ev.getY();
	  
	   if (ev.getAction() == MotionEvent.ACTION_DOWN) {
		   orignal_y = ev.getY();
		   if (getScrollY() == maxY) {
			  return false;
		   }else {
			   return super.onInterceptTouchEvent(ev);
		   }
	   } else if(ev.getAction() == MotionEvent.ACTION_MOVE){
		   if(flag) {
			   orignal_y = ev.getY();
			   return true;
		   }
		  if (getScrollY() == maxY ) {
			   return false;
		   } else if (getScrollY() == 0) {
			  if(last_y < temp_y) {
				  return true;
			  } else {
				  return false;
			  }
		   }
	   }
	   return super.onInterceptTouchEvent(ev);
	   
   }
   boolean flag = false;
   @Override
  public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
	// TODO Auto-generated method stub
	  
	 super.requestDisallowInterceptTouchEvent(disallowIntercept);
  }  
   public void requestInterceptTouchEvent(boolean disallowIntercept,MotionEvent ev) {
	   flag = true;
//	   orignal_y = ev.getY();
//	   ev.setAction(MotionEvent.ACTION_DOWN);
	   mPointerId = ev.getPointerId(0);
	   super.requestDisallowInterceptTouchEvent(disallowIntercept);
   }
 
}
