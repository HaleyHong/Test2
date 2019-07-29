package com.feyon.myapplication.MyCustomView;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by DS on 2018/7/18.
 */

public class CViewProgress2 extends View {
    private ValueAnimator valueAnimator;

    private Paint mPaintFg;
    private Paint mPaintBg;

    private TextPaint mPaintW;


    private RectF arcRectFg;
    private RectF arcRectBg;

    private int sweepAngle=0;
    private int startAngle=-90;

    private int maxAngle=0;
    //
    private int widSize=70;

    private String curScore;
    private String allScore;

    private float curScoreF;
    private float allScoreF;

    private AnimatorLoading animatorLoading;

    private Rect rectW;
    private String str="cesss\nsss";

    private float w;
    private float h;

    StaticLayout layout;

    public CViewProgress2(Context context) {
        super(context);
        init();
    }

    public CViewProgress2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CViewProgress2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    public void setAnimatorLoading(AnimatorLoading animatorLoading) {
        this.animatorLoading = animatorLoading;
    }

    private void init(){
        mPaintFg=new Paint();
        mPaintFg.setAntiAlias(true);
        mPaintFg.setColor(Color.YELLOW);
        mPaintFg.setStrokeWidth(widSize);
        mPaintFg.setStyle(Paint.Style.STROKE);



        mPaintBg=new Paint();
        mPaintBg.setAntiAlias(true);
        mPaintBg.setColor(Color.GRAY);
        mPaintBg.setStrokeWidth(widSize);
        mPaintBg.setStyle(Paint.Style.STROKE);

        arcRectFg=new RectF();
        arcRectBg=new RectF();

        setBackgroundColor(Color.WHITE);

        mPaintW=new TextPaint();
        mPaintW.setAntiAlias(true);
        mPaintW.setColor(Color.BLUE);
        mPaintW.setStyle(Paint.Style.STROKE);

        rectW=new Rect();
//        mPaintW.getTextBounds(str,0,str.length(),rectW);




    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (layout==null)
        {
            layout = new StaticLayout(str, mPaintW, w,
                    Layout.Alignment.ALIGN_CENTER, 1.0F, 0.0F, true);
        }
    }

    public void setScore(String curScore, String allScore){
        this.curScore=curScore;
        this.allScore=allScore;

        this.curScoreF=Float.parseFloat(curScore);
        this.allScoreF=Float.parseFloat(allScore);

        maxAngle= (int) (this.curScoreF/this.allScoreF*360);
        initValueAnimator();
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        arcRectFg.bottom=getHeight()-widSize/2;
        arcRectFg.left=widSize/2;
        arcRectFg.right=getWidth()-widSize/2;
        arcRectFg.top=widSize/2;

        arcRectBg.bottom=getHeight()-widSize/2;
        arcRectBg.left=widSize/2;
        arcRectBg.right=getWidth()-widSize/2;
        arcRectBg.top=widSize/2;

        w=getWidth();
        h=getHeight();
    }

    private void initValueAnimator(){

        final int end= (int) curScoreF;
        valueAnimator=ValueAnimator.ofInt(0,end);
        valueAnimator.setDuration(1000);
//        valueAnimator.setRepeatCount(0);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int v= (int) animation.getAnimatedValue();

                postRefresh(v);

                if (v==end)
                {
                    if (animatorLoading!=null)
                        animatorLoading.animatorLoadingCur(curScore);
                }else {
                    if (animatorLoading!=null)
                        animatorLoading.animatorLoadingCur(v+"");
                }

                postInvalidate();
            }


        });
    }

    private void postRefresh(int v){

        int angle= (int) (v/allScoreF*360);
        if (startAngle==-90)
        {
            sweepAngle=angle;
        }else {
            startAngle=sweepAngle;
            sweepAngle=angle;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


     canvas.drawArc(arcRectBg,0,360,false,mPaintBg);

     canvas.drawArc(arcRectFg,startAngle,sweepAngle,false,mPaintFg);

     float s=(rectW.bottom-rectW.top)/2+getBaseline(mPaintW);
     //文字绘制到整个布局的中心位置

//        canvas.translate(300,h/2-layout.getHeight()/2);

      layout.draw(canvas);

      canvas.restore();
    }



    /**
     * 计算绘制文字时的基线到中轴线的距离
     *
     * @param p
     * @return 基线和centerY的距离
     */
    public static float getBaseline(Paint p) {
        Paint.FontMetrics fontMetrics = p.getFontMetrics();
//        (FontMetrics.bottom - FontMetrics.top)/2 - FontMetrics.bottom
        return (fontMetrics.bottom - fontMetrics.top) / 2 -fontMetrics.bottom;
    }

    public void start(){
        if (valueAnimator!=null)
        valueAnimator.start();
    }

    public interface AnimatorLoading{
        void animatorLoadingCur(String cur);
    }



}
