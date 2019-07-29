package com.feyon.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;

public class Main40Activity extends AppCompatActivity {

    private static final String TAG = "Main40Activity";
    private View mView;
    private float x,y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main40);

        mView=findViewById(R.id.mview);
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                test();
            }
        });

    }

    public void test(){
       ViewPropertyAnimator animator= mView.animate();
       animator.setDuration(2000);
       animator.translationY(mView.getY()+200);
       animator.start();


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action= event.getAction();
        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
                x=event.getX();
                y=event.getY();
                Log.i(TAG, "onTouchEvent: "+x+":Y"+y);
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(event);
    }
}
