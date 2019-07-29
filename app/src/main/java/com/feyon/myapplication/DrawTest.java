package com.feyon.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by DS on 2018/4/3.
 */

public class DrawTest extends View {

    private Paint paint;
    private RectF rectF;

    private Rect rect;

    private float[] pts={10.0f,10.0f,20.0f,20.0f,30.0f,30.0f,40.0f,40.0f};

    public DrawTest(Context context) {
        this(context,null);
    }

    public DrawTest(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DrawTest(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setShadowLayer(5, 4, 5, Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);

        rectF = new RectF(200, 200, 400, 400);

        rect = new Rect(400, 400, 800, 800);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawLine(0,100,100,0,paint);

        canvas.drawCircle(100,100,50,paint);

        canvas.drawLines(pts,paint);

        canvas.drawRect(rectF,paint);

        canvas.drawRect(rect,paint);
    }
}
