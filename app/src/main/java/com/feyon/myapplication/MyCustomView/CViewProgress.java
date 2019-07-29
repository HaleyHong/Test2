package com.feyon.myapplication.MyCustomView;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by DS on 2018/7/18.
 */

public class CViewProgress extends View {
    private ValueAnimator valueAnimator;

    private int start_value;
    private int end_value;

    private String test="0%";
    private Rect test_rect;

    private Rect a_rect;

    private float cur;

    private int w,h;

    private Paint mPaint;
    private Paint mPaint2;
    private Paint mPaint3;
    private RectF rectF;

    private RectF arcRectF;
    private RectF arcRectF2;

    private int endAngle=0;
    private int startAngle=0;
    private int w_angle=0;

    public CViewProgress(Context context) {
        super(context);
        init();
    }

    public CViewProgress(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CViewProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init(){
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        test_rect=new Rect();
        mPaint.setTextSize(16);
        mPaint.getTextBounds(test,0,test.length(),test_rect);


        mPaint2=new Paint();
        mPaint2.setAntiAlias(true);
        mPaint2.setColor(Color.YELLOW);

        mPaint3=new Paint();
        mPaint3.setAntiAlias(true);
        mPaint3.setColor(Color.WHITE);
        mPaint3.setStyle(Paint.Style.STROKE);


        rectF=new RectF();



        arcRectF=new RectF();



        arcRectF2=new RectF();


        initValueAnimator();
    }

    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, r, b);
        arcRectF2.bottom=b;
        arcRectF2.left=l;
        arcRectF2.right=r;
        arcRectF2.top=t;

        arcRectF.bottom=b;
        arcRectF.left=l;
        arcRectF.right=r;
        arcRectF.top=t;

    }

    private void initValueAnimator(){
       /* valueAnimator=ValueAnimator.ofInt(0,100);
        valueAnimator.setDuration(1000*10);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int v= (int) animation.getAnimatedValue();

                test=v+"%";
                cur= (float) ((v/100.0)*w);

                endAngle=360*v/100;

                startAngle=endAngle;
                if (startAngle<=180)
                {
                    w_angle+=5;
                }else {
                    w_angle-=5;
                }

                postInvalidate();
            }
        });*/

        valueAnimator=ValueAnimator.ofInt(0,360);
        valueAnimator.setDuration(1000);
        valueAnimator.setRepeatCount(1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int v= (int) animation.getAnimatedValue();

//                test=v+"%";
//                cur= (float) ((v/100.0)*w);

                endAngle=v;

                startAngle=endAngle;

                if (startAngle==0)
                {
                    w_angle=5;
                }

                if (startAngle<=170)
                {
                    if (w_angle>5)
                    {
                        w_angle=w_angle-5;
                    }
                }else if(startAngle>180&&startAngle<350){
                    if (w_angle<20)
                    {
                        w_angle=w_angle+5;
                    }
                }

                Log.i("Taf", "onAnimationUpdate: "+w_angle);
                postInvalidate();
            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        this.w=w;
        this.h=h;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        rectF.right=cur;
        rectF.left=0;
        rectF.top=0;
        rectF.bottom=h;

        canvas.drawRect(rectF,mPaint2);


        float s=(test_rect.bottom-test_rect.top)/2+getBaseline(mPaint);
        //文字绘制到整个布局的中心位置
        canvas.drawText(test, cur-mPaint.measureText(test),s, mPaint);


        canvas.drawArc(arcRectF,0,endAngle,false,mPaint3);

        canvas.drawArc(arcRectF2,startAngle,w_angle,false,mPaint3);

    }



    /**
     * 计算绘制文字时的基线到中轴线的距离
     *
     * @param p
     * @return 基线和centerY的距离
     */
    public static float getBaseline(Paint p) {
        Paint.FontMetrics fontMetrics = p.getFontMetrics();
//        (FontMetrics.bottom - FontMetrics.top)/2 - FontMetrics.bottom
        return (fontMetrics.bottom - fontMetrics.top) / 2 -fontMetrics.bottom;
    }

    public void start(){
        valueAnimator.start();
    }
}
