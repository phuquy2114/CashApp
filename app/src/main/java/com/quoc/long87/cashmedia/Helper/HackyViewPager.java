package com.quoc.long87.cashmedia.Helper;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Hacky fix for Issue #4 and
 * http://code.google.com/p/android/issues/detail?id=18990
 * <p/>
 * ScaleGestureDetector seems to mess up the touch events, which means that
 * ViewGroups which make use of onInterceptTouchEvent throw a lot of
 * IllegalArgumentException: pointerIndex out of range.
 * <p/>
 * There's not much I can do in my code for now, but we can mask the result by
 * just catching the problem and ignoring it.
 * 
 * @author Chris Banes
 */
public class HackyViewPager extends ViewPager {
	
	private boolean enable;

	public HackyViewPager(Context context) {
		super(context);
		enable = true;
	}

	public HackyViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		enable = true;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		if (enable) {
			try {
	            return super.onInterceptTouchEvent(ev);
	        } catch (IllegalArgumentException e) {
	            e.printStackTrace();
	            return false;
	        }
		}
		
		return false;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		if (enable) {
			return super.onTouchEvent(arg0);
		}
		
		return false;
		
	}
	
	public void setEnabled(boolean enabled) {
		this.enable = enabled;
	}



}
