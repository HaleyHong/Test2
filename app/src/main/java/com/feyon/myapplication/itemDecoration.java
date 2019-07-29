package com.feyon.myapplication;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by DS on 2018/3/30.
 */

public abstract class itemDecoration extends RecyclerView.ItemDecoration{
    private Paint mpaint;

    private Paint mPaintText;

    public itemDecoration(Context context) {
        this.mpaint =new Paint();
        mpaint.setAntiAlias(true);
        mpaint.setColor(Color.BLUE);

        mPaintText=new Paint();
        mPaintText.setColor(Color.RED);
        mPaintText.setTextSize(20);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }


    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        System.out.println("all:"+parent.getChildCount());
        for (int i = 0; i < parent.getChildCount(); i++) {
            View view=parent.getChildAt(i);
            int position= parent.getChildAdapterPosition(view);
            int toptest=parent.getTop();
            System.out.println("position:"+position+"top:"+toptest);


            if (i==0)
            {
                int left=parent.getPaddingLeft();
                int top=parent.getTop();



                if (isLastOne(position))
                {
                    int ss= view.getBottom()-50;

                    if (top>ss)
                    {
                        System.out.println("Last:"+position);
                        top=ss;
                    }
                }
                int bottom=top+50;
                int right=parent.getWidth()-parent.getPaddingRight();
                c.drawRect(left,top,right,bottom,mpaint);
                c.drawText(setTextTile(position),10,top+25,mPaintText);

            }else if (isHeadView(position))
            {
                int left=parent.getPaddingLeft();
                int top=view.getTop()-50;
                int bottom=view.getTop();
                int right=parent.getWidth()-parent.getPaddingRight();
                c.drawRect(left,top,right,bottom,mpaint);

                c.drawText(setTextTile(position),10,top+25,mPaintText);

            }
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position=parent.getChildAdapterPosition(view);
        if (isHeadView(position))
        {
            outRect.set(50,50,50,50);
        }

    }

    public abstract boolean isHeadView(int position);

    public abstract String setTextTile(int position);

    public abstract boolean isLastOne(int position);
}
