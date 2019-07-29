package com.feyon.myapplication.MyCustomView;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

/**
 * Created by DS on 2018/7/17.
 */

@SuppressLint("AppCompatCustomView")
public class CTextView extends TextView {

    private String[] array;
    private String text;
    private StringBuffer stringBuffer;
    private int count;
    private ValueAnimator valueAnimator;
    private int cur=-1;

    public CTextView(Context context) {
        this(context,null);
    }

    public CTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    public void setCText(String s)
    {
        if (s==null)
            return;

        array=new String[s.length()];
        for (int i = 0; i <s.length() ; i++) {
            array[i]=s.substring(i,i+1);
        }

        count=s.length();


        valueAnimator=ValueAnimator.ofInt(0,count-1);
        valueAnimator.setDuration(count*1000);
        valueAnimator.setInterpolator(new LinearInterpolator());
        stringBuffer=new StringBuffer();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                int  value= (int) animation.getAnimatedValue();
                if (value!=cur)
                {
                    cur=value;
                    Log.i("TAG", "onAnimationUpdate: "+cur);
                    stringBuffer.append(array[cur]);
                    //不用换行
                    setText(stringBuffer.toString());
                }

            }
        });
    }

    private void init(){

    }

    public void start(){
        valueAnimator.start();


    }

}
