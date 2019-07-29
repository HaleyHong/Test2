package com.feyon.myapplication;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class MyLinearyout extends LinearLayout {
    private String TAG="Test";

    public MyLinearyout(Context context) {
        super(context);

    }

    public MyLinearyout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    public MyLinearyout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(TAG, "MyLinearyout_dispatchTouchEvent: ");
        requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i(TAG, "MyLinearyout_onInterceptTouchEvent: ");
//        switch (ev.getAction())
//        {
//            case MotionEvent.ACTION_DOWN:
////                requestDisallowInterceptTouchEvent(true);
//                return false;
//        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "MyLinearyout_onTouchEvent: ");
        return super.onTouchEvent(event);
    }


}
