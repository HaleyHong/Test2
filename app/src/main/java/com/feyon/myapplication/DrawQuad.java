package com.feyon.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by DS on 2018/4/4.
 */

public class DrawQuad extends View {
    private Paint mPaint;
    private Path mPath;

    private float LastX,LastY;

    public DrawQuad(Context context) {
        this(context,null);
    }

    public DrawQuad(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DrawQuad(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint=new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(15);

        mPath=new Path();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:

                mPath.moveTo(event.getX(),event.getY());
                LastX=event.getX();
                LastY=event.getY();

                return true;

            case MotionEvent.ACTION_MOVE:
                float endX=(LastX+event.getX())/2;
                float endY=(LastY+event.getY())/2;
                mPath.quadTo(LastX,LastY,endX,endY);

                LastX=event.getX();
                LastY=event.getY();

                postInvalidate();
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPath(mPath,mPaint);
    }
}
