package com.feyon.myapplication;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class FleaMarkTextView extends ViewGroup {
    private float screenWidth;
    public FleaMarkTextView(Context context) {
        super(context);
        init();
    }

    public FleaMarkTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FleaMarkTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }



    private void init(){

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        measureChildren(widthMeasureSpec,heightMeasureSpec);
        int parentSizeW=MeasureSpec.getSize(widthMeasureSpec);
        //可用宽
        int kc=MeasureSpec.getSize(widthMeasureSpec);
        //可用高
        int kh=MeasureSpec.getSize(heightMeasureSpec);

        //最终总高度
        int w=0;
        int h=0;
        for (int i = 0; i < getChildCount(); i++) {
            View child=getChildAt(i);



            //最后一个时
            if (i+1>=getChildCount())
            {

                    int wSpec=MeasureSpec.makeMeasureSpec(kc,MeasureSpec.getMode(widthMeasureSpec));
                    //measureChildWithMargins 计算时会考虑margin下时再给于可用宽高
                    measureChildWithMargins(child,wSpec,0,heightMeasureSpec,0);
                    int cw=child.getMeasuredWidth();
                    MarginLayoutParams marginLayoutParams= (MarginLayoutParams) child.getLayoutParams();
                    int lr=marginLayoutParams.leftMargin+marginLayoutParams.rightMargin;

                    //小于一般时 重新测量
                    if (w+cw+lr<=parentSizeW/2){
                        kc=parentSizeW/2-w;
                        w=parentSizeW/2;
                        int wSpec2=MeasureSpec.makeMeasureSpec(kc,MeasureSpec.getMode(widthMeasureSpec));
                        measureChildWithMargins(child,wSpec2,0,heightMeasureSpec,0);
                        MarginLayoutParams marginLayoutParams2= (MarginLayoutParams) child.getLayoutParams();

                        int ch2=child.getMeasuredHeight();
                        h=Math.max(ch2+marginLayoutParams2.topMargin+marginLayoutParams2.bottomMargin,h);
                    }else {
                        int ch=child.getMeasuredHeight();
                        w=parentSizeW;
                        h=Math.max(ch+marginLayoutParams.topMargin+marginLayoutParams.bottomMargin,h);
                    }

            }else {
                int wSpec=MeasureSpec.makeMeasureSpec(kc,MeasureSpec.getMode(widthMeasureSpec));
                //measureChildWithMargins 计算时会考虑margin下时再给于可用宽高
                measureChildWithMargins(child,wSpec,0,heightMeasureSpec,0);
                int cw=child.getMeasuredWidth();
                int ch=child.getMeasuredHeight();

                MarginLayoutParams marginLayoutParams= (MarginLayoutParams) child.getLayoutParams();
                int lr=marginLayoutParams.leftMargin+marginLayoutParams.rightMargin;
                kc=kc-cw-lr;

                w=w+cw+marginLayoutParams.rightMargin+marginLayoutParams.leftMargin;

                h=Math.max(ch+marginLayoutParams.topMargin+marginLayoutParams.bottomMargin,h);
            }

        }
        setMeasuredDimension(100,h);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int w=0;
        int h=0;
        int aw=r-l;
        int ah=b-t;
        for (int i = 0; i <getChildCount() ; i++) {
            View child=getChildAt(i);
            MarginLayoutParams marginLayoutParams= (MarginLayoutParams) child.getLayoutParams();
            int cw=child.getMeasuredWidth();
            int ch=child.getMeasuredHeight();

            int top=marginLayoutParams.topMargin;
            int left=w+marginLayoutParams.leftMargin;
            int right=left+cw;
            int bottom=top+ch;

            child.layout(left,top,right,bottom);

            w=right+marginLayoutParams.rightMargin;
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }
}
