package com.feyon.myapplication;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class MyLinearView extends ViewGroup {
    private int wAll;
    private int hAll;
    public MyLinearView(Context context) {
        super(context);
    }

    public MyLinearView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int hM=MeasureSpec.getMode(heightMeasureSpec);
        int h=MeasureSpec.getSize(heightMeasureSpec);


        int wM=MeasureSpec.getMode(widthMeasureSpec);
        int w=MeasureSpec.getSize(widthMeasureSpec);
        System.out.println();
//
        measureChildren(widthMeasureSpec,heightMeasureSpec);
//
//
        hAll=0;
        wAll=0;
        int rowW=0;
        int rowH=0;
        int line=0;
        for (int i = 0; i < getChildCount(); i++) {
            View view=getChildAt(i);
            MarginLayoutParams marginLayoutParams= (MarginLayoutParams) view.getLayoutParams();
            int mViewH=view.getMeasuredHeight()+marginLayoutParams.bottomMargin+marginLayoutParams.topMargin;
            int mViewW=view.getMeasuredWidth()+marginLayoutParams.leftMargin+marginLayoutParams.rightMargin;




            //大于最大的边界
            if (rowW+mViewW>w)
            {
                //h换行
                hAll+=mViewH;

                //行数—+1
                line++;

                //重置
                rowW=mViewW;
                rowH=mViewH;
            }else {
                rowW+=mViewW;
            }

            rowH=Math.max(mViewH,rowH);
            if (line==0)
            {
                hAll=rowH;
            }
//            if (i+1>=getChildCount()&&line==0)
//            {
//                hAll+=rowH;
//            }

            //记录当前最大的行宽
            wAll=Math.max(rowW,wAll);
        }
        setMeasuredDimension(wM==MeasureSpec.AT_MOST?wAll:w,hM==MeasureSpec.AT_MOST?hAll:h);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        int row=getWidth();
        int left=0;
        int top=0;
        int rowMax=0;
        for (int j = 0; j <getChildCount() ; j++) {
            View view=getChildAt(j);

            MarginLayoutParams marginLayoutParams= (MarginLayoutParams) view.getLayoutParams();
            int mViewH=view.getMeasuredHeight()+marginLayoutParams.bottomMargin+marginLayoutParams.topMargin;
            int mViewW=view.getMeasuredWidth()+marginLayoutParams.leftMargin+marginLayoutParams.rightMargin;


            if (left+mViewW>row)
            {
                //换行
                left=0;
                top+=rowMax;
            }
            rowMax=Math.max(rowMax,mViewH);

            view.layout(left,top,mViewW+left,mViewH+top);

            left+=mViewW;
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
