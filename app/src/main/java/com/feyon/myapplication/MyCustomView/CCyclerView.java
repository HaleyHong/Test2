package com.feyon.myapplication.MyCustomView;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

/**
 * Created by DS on 2018/7/17.
 */

@SuppressLint("AppCompatCustomView")
public class CCyclerView extends Button {
    private Paint paint;
    private Paint paint2;
    private int centreX,centreY;
    private int cur=-1;

    private ValueAnimator valueAnimator;

    private int radius;

    public CCyclerView(Context context) {
        super(context);

        init();
    }

    public CCyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public CCyclerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width=MeasureSpec.getSize(widthMeasureSpec);
        int height=MeasureSpec.getSize(heightMeasureSpec);

        centreX=width/2;
        centreY=height/2;

        radius=height/8;
    }

    private void init(){
        paint=new Paint();
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);

        valueAnimator=ValueAnimator.ofInt(0,3);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setDuration(500*3);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value= (int) animation.getAnimatedValue();
                Log.i("TAG", "onAnimationUpdate: "+value);
                if (value!=cur)
                {
                    cur=value;
                    postInvalidate();
                }
            }
        });


        paint2=new Paint();
        paint2.setColor(Color.WHITE);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (cur)
        {
            case 0:
                canvas.drawCircle(centreX-radius*4,centreY,radius,paint2);
                canvas.drawCircle(centreX,centreY,radius,paint);
                canvas.drawCircle(centreX+radius*4,centreY,radius,paint);
                break;
            case 1:
                canvas.drawCircle(centreX-radius*4,centreY,radius,paint);
                canvas.drawCircle(centreX,centreY,radius,paint2);
                canvas.drawCircle(centreX+radius*4,centreY,radius,paint);
                break;
            case 2:
                canvas.drawCircle(centreX-radius*4,centreY,radius,paint);
                canvas.drawCircle(centreX,centreY,radius,paint);
                canvas.drawCircle(centreX+radius*4,centreY,radius,paint2);
                break;
            default:
                canvas.drawCircle(centreX-radius*4,centreY,radius,paint);
                canvas.drawCircle(centreX,centreY,radius,paint);
                canvas.drawCircle(centreX+radius*4,centreY,radius,paint);
        }




    }

    public void start()
    {
        if (valueAnimator.isRunning())
        {
            stop();
        }else {
            valueAnimator.start();
        }
    }

    public void stop(){
        valueAnimator.end();
        valueAnimator.cancel();
    }
}
