package com.tmd.dictionary.util.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import static com.tmd.dictionary.staticfinal.ConstantValue.MY_TAG;

/**
 * Created by tmd on 20/08/2017.
 */
public class NonswipeableViewPager extends ViewPager {
    public NonswipeableViewPager(Context context) {
        super(context);
    }

    public NonswipeableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private OnTouchListener mSuppressInterceptListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN && v instanceof ViewGroup) {
                ((ViewGroup) v).requestDisallowInterceptTouchEvent(true);
                Log.e(MY_TAG, "onTouch: ");
            }
            Log.e(MY_TAG, "onTouch:  return false;");
            return false;
        }
    };

    @Override
    public boolean canScrollHorizontally(int direction) {
        return false;
    }
}
