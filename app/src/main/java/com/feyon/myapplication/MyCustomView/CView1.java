package com.feyon.myapplication.MyCustomView;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


/**
 * Created by DS on 2018/7/17.
 */

public class CView1 extends View{

    private int width;
    private int height;

    private Paint mPaint;

    private RectF rect;

    private ValueAnimator valueAnimator;
    private ValueAnimator valueAnimator2;

    private int radio;

    private int c_width=0;

    private int d_radio=0;
    private int d_width=0;


    public CView1(Context context) {
        super(context);

        init();
    }

    public CView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public CView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//
//        width=MeasureSpec.getSize(widthMeasureSpec);
//        height=MeasureSpec.getSize(heightMeasureSpec);
//
//    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        if (w>h)
        {
            d_radio=h/2;
            d_width=(w-h)/2;
        }else {
            d_radio=w/2;
            d_width=(h-w)/2;
        }
        width=w;
        height=h;
        rect=new RectF(0,0,w,h);

        initAnimation();
        initAnimation2();
    }

    private void init(){
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLUE);

    }

    private void initAnimation(){
        valueAnimator=ValueAnimator.ofInt(0,d_radio);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                radio= (int) animation.getAnimatedValue();

                postInvalidate();

                Log.i("TAG1", "onAnimationUpdate: ");
            }
        });
    }

    private void initAnimation2(){
        valueAnimator2=ValueAnimator.ofInt(0,d_width);
        valueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                c_width= (int) animation.getAnimatedValue();

                postInvalidate();
                Log.i("TAG2", "onAnimationUpdate: ");
            }
        });
    }

    public void start(){

        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.setDuration(1000);
        animatorSet.play(valueAnimator).with(valueAnimator2);
        animatorSet.start();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.drawRect(rect,mPaint);
        change();

        canvas.drawRoundRect(rect,radio,radio,mPaint);
    }

    private void change()
    {
        if (height>width)
        {
            rect.top=c_width;
            rect.bottom=height-c_width;
        }else {
            rect.left=c_width;
            rect.right=width-c_width;
        }

    }
}
