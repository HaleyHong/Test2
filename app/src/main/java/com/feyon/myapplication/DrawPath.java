package com.feyon.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by DS on 2018/4/3.
 */

public class DrawPath extends View {
    private Paint mPaint;

    private Paint mPaintText;

    private RectF rectF;

    private Path path;
    public DrawPath(Context context) {
        this(context,null);
    }

    public DrawPath(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DrawPath(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(20);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);

        mPaintText=new Paint();
        mPaintText.setTextSize(20);
        mPaintText.setColor(Color.BLACK);

        rectF=new RectF(100,100,500,500);

        path=new Path();



    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        path.moveTo(200,200);
//        path.lineTo(200,400);
//        path.lineTo(400,400);
//        path.close();
//        canvas.drawPath(path,mPaint);

        path.addOval(rectF, Path.Direction.CCW);

        canvas.drawTextOnPath("努力！ 加油！有关截取部分字体绘制相关参数（index,count），没难度，就不再讲了，下面首重讲",path,0,0,mPaintText);
    }
}
