package com.feyon.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by DS on 2018/4/8.
 */

public class custom_viewGroup extends ViewGroup {
    public custom_viewGroup(Context context) {
        super(context);
    }

    public custom_viewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int wSize=MeasureSpec.getSize(widthMeasureSpec);
        int wMode=MeasureSpec.getMode(widthMeasureSpec);

        int hSize=MeasureSpec.getSize(heightMeasureSpec);
        int hMode=MeasureSpec.getMode(heightMeasureSpec);

        measureChildren(widthMeasureSpec,heightMeasureSpec);

        int allWidth=0;
        int allHeight=0;


        for (int i = 0; i < getChildCount(); i++) {
            View view=getChildAt(i);
            MarginLayoutParams marginLayoutParams= (MarginLayoutParams) view.getLayoutParams();

            allWidth+=view.getMeasuredWidth()+marginLayoutParams.leftMargin+marginLayoutParams.rightMargin;

            allHeight+=view.getMeasuredHeight()+marginLayoutParams.topMargin+marginLayoutParams.bottomMargin;
        }


        setMeasuredDimension((wMode==MeasureSpec.EXACTLY?wSize:allWidth),(hMode==MeasureSpec.EXACTLY?hSize:allHeight));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        MarginLayoutParams marginLayoutParams;
        for (int i = 0; i < getChildCount(); i++) {
            View view=getChildAt(i);
            marginLayoutParams= (MarginLayoutParams) view.getLayoutParams();
            switch (i)
            {
                case 0:
                    view.layout(marginLayoutParams.leftMargin,marginLayoutParams.topMargin,view.getMeasuredWidth()+marginLayoutParams.leftMargin,view.getMeasuredHeight()+marginLayoutParams.topMargin);
                    break;
                case 1:
                    int cl=getMeasuredWidth()-view.getMeasuredWidth()-marginLayoutParams.rightMargin;
                    view.layout(cl,marginLayoutParams.topMargin,getMeasuredWidth()-marginLayoutParams.rightMargin,view.getMeasuredHeight()+marginLayoutParams.topMargin);
                    break;
            }
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
