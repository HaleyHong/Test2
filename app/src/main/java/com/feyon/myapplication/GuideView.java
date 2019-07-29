package com.feyon.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

public class GuideView extends FrameLayout {
    public RectF rectF;
    public Path mPath;
    public Paint mPaint;
    private Paint mPaint2;
    private int h,w;
    public GuideView(@NonNull Context context) {
        super(context);
        init();
    }

    public GuideView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GuideView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){

        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
//        setBackgroundColor(ContextCompat.getColor(getContext(),R.color.blue));

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
               ViewGroup parentView= (ViewGroup) GuideView.this.getParent();
               parentView.removeView(GuideView.this);
            }
        });
        mPaint=new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
//        mPaint.setColor(ContextCompat.getColor(getContext(),R.color.half_transparent));
        mPaint.setStrokeWidth(15);

        mPaint2=new Paint();
        mPaint2.setStyle(Paint.Style.FILL);
        mPaint2.setAntiAlias(true);
        mPaint2.setColor(Color.BLACK);
        mPaint2.setStrokeWidth(15);

        setWillNotDraw(false);
    }

    public void add(View view)
    {
        rectF=getRectFOnScreen(view);
        TextView textView=new TextView(getContext());
        textView.setText("测试");
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        int s=textView.getWidth();
        textView.setX(rectF.centerX()-textView.getMeasuredWidth()/2);
        textView.setY(view.getY()+view.getMeasuredHeight());

        addView(textView);

        postInvalidate();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        h=getHeight();
        w=getWidth();
    }

    public RectF getRectFOnScreen(View view)
    {
        if (view==null)
        {
            return new RectF();
        }

        RectF rectF=new RectF();
        int[] pos=new int[2];
        view.getLocationOnScreen(pos);
        rectF.left=pos[0];
        rectF.top=pos[1];
        rectF.right=rectF.left+view.getMeasuredWidth();
        rectF.bottom=rectF.top+view.getMeasuredHeight();

        return rectF;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.save();
//        if (mPath!=null)
//        canvas.drawRect(0, 0, 200, 200,mPaint);
        canvas.save();
        if (rectF!=null)
        {
            canvas.clipRect(rectF,Region.Op.DIFFERENCE);
            canvas.drawColor(ContextCompat.getColor(getContext(),R.color.half_transparent));
        }
        canvas.restore();


//        canvas.drawRect(0, 0, 200, 200,mPaint);
//        canvas.drawColor(Color.RED);
//        canvas.restore();
    }
}
