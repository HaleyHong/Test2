package com.feyon.myapplication.scroller;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by DS on 2018/9/12.
 */

public class lLingearLayout extends LinearLayout {

    private Scroller scroller;
    private GestureDetector gestureDetector;
    public lLingearLayout(Context context) {
        super(context);
        init();
    }

    public lLingearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public lLingearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        scroller=new Scroller(getContext());
        setClickable(true);
        setLongClickable(true);
        gestureDetector=new GestureDetector(getContext(),new GestureDetector.SimpleOnGestureListener()
        {

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

                startAnimation(scroller.getFinalX(),scroller.getFinalY(),0, (int) (distanceY/2));

                return false;
            }
        });
    }

    private void startAnimation(int startX, int startY, int dx, int dy){
        scroller.startScroll(startX,startY,dx,dy);
        invalidate();
    }

    public void reset(int x,int y)
    {
        startAnimation(scroller.getFinalX(),scroller.getFinalY(),x-scroller.getFinalX(),y-scroller.getFinalY());
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset())
        {
            scrollTo(scroller.getCurrX(),scroller.getCurrY());
            postInvalidate();
        }
        super.computeScroll();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_UP:
                reset(0,0);
                break;
            default:
                gestureDetector.onTouchEvent(event);
        }
        return true;
    }
}
