package com.feyon.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by DS on 2018/4/3.
 */

public class DrawRegin extends View {

    private Rect rect;
    private Region region;
    public DrawRegin(Context context) {
        this(context,null);
    }

    public DrawRegin(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DrawRegin(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        rect=new Rect(100,100,200,200);
        region.set(rect);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


    }


    private void drawRegion(Canvas canvas, Region region, Paint paint)
    {
        RegionIterator regionIterator=new RegionIterator(region);
        Rect rect=new Rect();
        while (regionIterator.next(rect))
        {
            canvas.drawRect(rect,paint);
        }
    }


//    private void drawRegion(Canvas canvas,Region rgn,Paint paint)
//    {
//        RegionIterator iter = new RegionIterator(rgn);
//        Rect r = new Rect();
//
//        while (iter.next(r)) {
//            canvas.drawRect(r, paint);
//        }
//    }
}
