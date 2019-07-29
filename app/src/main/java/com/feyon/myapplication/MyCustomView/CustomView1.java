package com.feyon.myapplication.MyCustomView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.feyon.myapplication.R;

/**
 * Created by DS on 2018/7/16.
 */

public class CustomView1 extends View {
    private String text1;
    private int color;
    private int textSize;

    private Paint paint;
    private Rect rect;




    private int mheight;
    private int mwidth;





    public CustomView1(Context context) {
        this(context,null);
    }

    public CustomView1(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.CustomView1,defStyleAttr,0);
        int count=typedArray.getIndexCount();
        for (int i = 0; i < count; i++) {
            int attr=typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.CustomView1_ctext1:
                    text1 = typedArray.getString(attr);
                    break;
                case R.styleable.CustomView1_ccolor1:
                    color = typedArray.getColor(attr, Color.BLUE);
                    break;
                case R.styleable.CustomView1_ctextsize:
                   textSize= (int) typedArray.getDimension(attr,TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,16,context.getResources().getDisplayMetrics()));
                    break;
            }
        }

        typedArray.recycle();
        init();
    }

    private void init(){
        paint=new Paint();
        paint.setColor(color);
        paint.setAntiAlias(true);
        paint.setTextSize(textSize);

        rect=new Rect();
        paint.getTextBounds(text1,0,text1.length(),rect);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//
        int height=MeasureSpec.getSize(heightMeasureSpec);
        int heightMod=MeasureSpec.getMode(heightMeasureSpec);

        int  width=MeasureSpec.getSize(widthMeasureSpec);
        int widthMod=MeasureSpec.getMode(widthMeasureSpec);

        switch (heightMod)
        {
            case MeasureSpec.EXACTLY:
                mheight=height;
                break;
            case MeasureSpec.AT_MOST:
                mheight=rect.height()+getPaddingBottom()+getPaddingTop();
                break;
            case MeasureSpec.UNSPECIFIED:
                mheight=height;
                break;
        }

        switch (widthMod)
        {
            case MeasureSpec.EXACTLY:
                mwidth=width;
                break;
            case MeasureSpec.AT_MOST:
                mwidth=rect.width()+getPaddingLeft()+getPaddingRight();
                break;
            case MeasureSpec.UNSPECIFIED:
                mwidth=width;
                break;
        }

//        int w= MeasureSpec.makeMeasureSpec(mwidth,widthMod);
//        int h=MeasureSpec.makeMeasureSpec(mheight,height);

        setMeasuredDimension(mwidth,mheight);
//        super.onMeasure(w,h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(text1,getWidth()/2-rect.width()/2,getHeight()/2,paint);
    }
}
