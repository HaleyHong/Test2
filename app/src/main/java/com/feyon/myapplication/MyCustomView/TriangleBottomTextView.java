package com.feyon.myapplication.MyCustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by DS on 2018/11/12.
 */

public class TriangleBottomTextView extends android.support.v7.widget.AppCompatTextView {
    private Paint mPaint;
    private int paddingBottom=20;
    private Path path;

    private int h,w;
    public TriangleBottomTextView(Context context) {
        super(context);

        init();
    }

    public TriangleBottomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public TriangleBottomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init(){
        mPaint=new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(16);

        setPadding(getPaddingLeft(),getPaddingTop(),getPaddingRight(),getPaddingBottom()+paddingBottom);

        path=new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.h=h;
        this.w=w;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path.moveTo(0, h);// 此点为多边形的起点
        path.lineTo(w/2, h-20);
        path.lineTo(w, h);
        path.close(); // 使这些点构成封闭的多边形
        canvas.drawPath(path, mPaint);
    }
}
