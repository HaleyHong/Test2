package com.feyon.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by DS on 2018/4/4.
 */

public class DrawQuad2 extends View {

    private Paint paint;
    private Path path;

    public DrawQuad2(Context context) {
        this(context,null);
    }

    public DrawQuad2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DrawQuad2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint=new Paint();
        paint.setStrokeWidth(15);
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);

        paint.setStyle(Paint.Style.STROKE);

        path=new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        path.moveTo(0,300);

        path.rQuadTo(150,-200,300,0);

        path.rQuadTo(150,200,300,0);

        path.close();
        canvas.drawPath(path,paint);
    }
}
