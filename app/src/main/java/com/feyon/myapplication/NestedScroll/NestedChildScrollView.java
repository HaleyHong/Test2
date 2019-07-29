package com.feyon.myapplication.NestedScroll;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingChild2;
import android.support.v4.view.NestedScrollingChildHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class NestedChildScrollView extends LinearLayout implements NestedScrollingChild2 {
    private NestedScrollingChildHelper helper;
    private int[] mNestedOffsets = new int[2];
    public NestedChildScrollView(Context context) {
        super(context);
    }

    public NestedChildScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NestedChildScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(){
        helper= new NestedScrollingChildHelper(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final MotionEvent vtev=MotionEvent.obtain(event);
        final int action=event.getAction();
        final int actionIndex=event.getActionIndex();
        if (action==MotionEvent.ACTION_DOWN){
            mNestedOffsets[0]=mNestedOffsets[1]=0;
        }
        vtev.offsetLocation(mNestedOffsets[0],mNestedOffsets[1]);

        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean startNestedScroll(int axes, int type) {
        return helper.startNestedScroll(axes,type);
    }

    @Override
    public void stopNestedScroll(int type) {
        helper.stopNestedScroll(type);
    }

    @Override
    public boolean hasNestedScrollingParent(int type) {
        return helper.hasNestedScrollingParent(type);
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @Nullable int[] offsetInWindow, int type) {
        return helper.dispatchNestedScroll(dxConsumed,dyConsumed,dxUnconsumed,dyUnconsumed,offsetInWindow,type);
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, @Nullable int[] consumed, @Nullable int[] offsetInWindow, int type) {
        return helper.dispatchNestedPreScroll(dx,dy,consumed,offsetInWindow,type);
    }
}
