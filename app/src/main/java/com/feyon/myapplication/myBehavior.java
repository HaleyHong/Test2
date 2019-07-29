package com.feyon.myapplication;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class myBehavior extends CoordinatorLayout.Behavior<TextView> {
    public myBehavior() {
    }

    public myBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, TextView child, View dependency) {
        if (dependency instanceof Button)
            return true;
        return super.layoutDependsOn(parent, child, dependency);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, TextView child, View dependency) {

        return super.onDependentViewChanged(parent, child, dependency);
    }
}
