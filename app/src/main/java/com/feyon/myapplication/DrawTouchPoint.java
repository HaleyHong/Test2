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

public class DrawTouchPoint extends View {
    private Paint paint;
    private Path path;

    private long time;
    public DrawTouchPoint(Context context) {
        this(context,null);
    }

    public DrawTouchPoint(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DrawTouchPoint(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint=new Paint();
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);

        path=new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPath(path,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction()==MotionEvent.ACTION_DOWN)
        {
            path.moveTo(event.getX(),event.getY());


            return true;
        }else if (event.getAction()==MotionEvent.ACTION_MOVE) {
            path.lineTo(event.getX(), event.getY());
            postInvalidate();


            return true;
        }
//        }if (event.getAction()==MotionEvent.ACTION_UP)
//        {
//            if (time!=0&&System.currentTimeMillis()-time<=2000)
//            {
//                time=0;
//                reset();
//            }else {
//                time=System.currentTimeMillis();
//            }
//        }
        return super.onTouchEvent(event);

    }

    private void reset(){
        path.reset();
        postInvalidate();
    }

}
