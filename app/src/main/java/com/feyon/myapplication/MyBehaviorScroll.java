package com.feyon.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;

public class MyBehaviorScroll extends CoordinatorLayout.Behavior {
    public MyBehaviorScroll() {
    }

    public MyBehaviorScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }


    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        if (dy>0)
        {
            //向上滑动
            float halfOfDis = dy / 3.0f;
            int h=child.getHeight();
//            Log.i("TAG", "onNestedPreScroll: UP");
            if (child.getTranslationY()<=-h+child.getMinimumHeight())
            {
                child.setTranslationY(-h+child.getMinimumHeight());
            }else {
                if (child.getTranslationY()-halfOfDis<=-h+child.getMinimumHeight())
                {
                    child.setTranslationY(-h+child.getMinimumHeight());
                }else {
                    child.setTranslationY(child.getTranslationY()-halfOfDis);
                }
                consumed[1]=dy;
            }
        }else {
            //向上滑动
            if (target instanceof NestedScrollView)
            {   NestedScrollView nestedScrollView= (NestedScrollView) target;
                  if (nestedScrollView.getScrollY()<=0)
                  {
                      float halfOfDis = dy / 3.0f;
                      //
                      if (child.getTranslationY()<0)
                      {
                          if (child.getTranslationY()>=0)
                          {
                              child.setTranslationY(0);
                          }else {
                              child.setTranslationY(child.getTranslationY()-halfOfDis);
                          }
                          consumed[1]=dy;
                      }else {
                          child.setTranslationY(0);
                      }
                  }
            }

        }

    }

    public int getMaxRangeScroll(View child){
        int h=child.getHeight();
        return h-child.getMinimumHeight();
    }

    public boolean canScroll(View child, float pendingDy){
        //向下滑动 ViewPage已经偏移
        int pendingTranslationY = (int) (child.getTranslationY() - pendingDy);
        if (pendingTranslationY >= getMaxRangeScroll(child) && pendingTranslationY <= 0) {
            return true;
        }
        return false;
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
    }



    @Override
    public boolean onNestedFling(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, float velocityX, float velocityY, boolean consumed) {
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
    }

}
