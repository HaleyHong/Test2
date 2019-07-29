package com.feyon.myapplication;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyBehaviorHead extends CoordinatorLayout.Behavior {
    private float s;
    private long time;
    public MyBehaviorHead() {
    }

    public MyBehaviorHead(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {

        return dependency instanceof TextView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        child.setTranslationY((int)(dependency.getTranslationY()));
        return false;
    }

    @Override
    public boolean onMeasureChild(CoordinatorLayout parent, View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        ViewGroup.LayoutParams layoutParams= child.getLayoutParams();
        layoutParams.height=View.MeasureSpec.getSize(parentHeightMeasureSpec);
        return super.onMeasureChild(parent, child, parentWidthMeasureSpec, widthUsed, parentHeightMeasureSpec, heightUsed);
    }
}
