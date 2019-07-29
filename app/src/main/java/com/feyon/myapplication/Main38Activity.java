package com.feyon.myapplication;

import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Main38Activity extends AppCompatActivity {

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main38);

        button=findViewById(R.id.hello);
        button.post(new Runnable() {
            @Override
            public void run() {
                RectF rectF=getRectOnScreen(button);
                init(rectF);
            }
        });
    }

    public void init(RectF rectF){
        ViewGroup viewGroup=findViewById(android.R.id.content);

        GuideView guideView=new GuideView(this);
        viewGroup.addView(guideView);
        guideView.add(button);
//        Button button1=new Button(this);
//        button1.setText("nihao");
//        ViewGroup.LayoutParams layoutParams=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
//        button1.setX(rectF.left);
////        button1.setTranslationX(0);
//        button1.setY(rectF.bottom);
//        viewGroup.addView(button1,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
//       float xx=button1.getX();
//       float xt=button1.getTranslationX();
//        System.out.println();
    }

    public RectF getRectOnScreen(View view)
    {
        if (view==null)
        {
            new RectF();
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
}
