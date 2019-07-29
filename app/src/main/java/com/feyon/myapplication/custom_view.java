package com.feyon.myapplication;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by DS on 2018/4/8.
 */

public class custom_view extends View {
    public custom_view(Context context) {
        super(context);
    }

    public custom_view(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
