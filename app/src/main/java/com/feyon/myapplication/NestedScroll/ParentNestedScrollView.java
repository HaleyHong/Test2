package com.feyon.myapplication.NestedScroll;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingParent2;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

public class ParentNestedScrollView extends FrameLayout implements NestedScrollingParent2 {

    private String TAG="ParentNestedScrollView";
    public int scrollUpHeight=200;

    public boolean upAction,downAction;
//    public RecyclerView recyclerView;

    public ParentNestedScrollView(Context context) {
        super(context);

        init();
    }

//    public void setRecyclerView(RecyclerView recyclerView) {
//        this.recyclerView = recyclerView;
//    }

    public ParentNestedScrollView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public ParentNestedScrollView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    //设置向上滚动的距离
    public void setScrollUpHeight(final int scrollUpHeight) {
        if (scrollUpHeight==this.scrollUpHeight)
            return;
        post(new Runnable() {
            @Override
            public void run() {
                //变高
//                int h= getHeight();
//                ViewGroup.LayoutParams layoutParams= getLayoutParams();
//                layoutParams.height=h-ParentNestedScrollView.this.scrollUpHeight+scrollUpHeight;
//                setLayoutParams(layoutParams);
                ParentNestedScrollView.this.scrollUpHeight = scrollUpHeight;

                requestLayout();
            }
        });
    }

    private void init(){

    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

       int hMode= MeasureSpec.getMode(heightMeasureSpec);
       int height=MeasureSpec.getSize(heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(heightMeasureSpec+scrollUpHeight,hMode));

//        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),height+scrollUpHeight);

    }






    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes, int type) {
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0&&scrollUpHeight!=0;
    }

    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes, int type) {

    }

    @Override
    public void onStopNestedScroll(@NonNull View target, int type) {

    }

    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        int topDen= (int) Math.abs(getTranslationY());

        //上拉
        upAction=dy>0&&(topDen<=scrollUpHeight);
        if (upAction)
        {
            //dy大于最小距离
            if (topDen+dy>=scrollUpHeight)
            {
                dy= scrollUpHeight-topDen;
                if (dy<0)
                {
                    dy=0;
                }
            }
            consumed[1]=dy;
            setTranslationY(getTranslationY()-dy);
            return;
        }

        //下拉
        downAction=dy<0&&topDen<=scrollUpHeight;
        if (downAction)
        {
            if (topDen+dy<=0)
            {
                dy=-topDen;
            }
            consumed[1]=dy;
            setTranslationY(getTranslationY()-dy);
        }
    }

    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {

    }


}
