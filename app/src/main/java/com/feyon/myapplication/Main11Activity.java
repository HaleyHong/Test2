package com.feyon.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

public class Main11Activity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private View view;
    private String TAG=getClass().getName();
    private GestureDetector gestureDetector;
    private GestureDetector.OnDoubleTapListener onDoubleTapListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main11);



//        View view=findViewById(R.id.hahaha);
        onDoubleTapListener= new GestureDetector.OnDoubleTapListener() {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                Log.i(TAG, "onSingleTapConfirmed: ");
                return false;
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                Log.i(TAG, "onDoubleTap: ");
                return false;
            }

            @Override
            public boolean onDoubleTapEvent(MotionEvent e) {
                Log.i(TAG, "onDoubleTapEvent: ");
                return false;
            }
        };

        gestureDetector=new GestureDetector(this);
       gestureDetector.setOnDoubleTapListener(onDoubleTapListener);


        ScaleGestureDetector scaleGestureDetector=new ScaleGestureDetector(this,new text2());



    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

//    @Override
//    public boolean onDown(MotionEvent e) {
//        Log.i(TAG, "onDown: ");
//        return false;
//    }
//
//    @Override
//    public void onShowPress(MotionEvent e) {
//        Log.i(TAG, "onShowPress: ");
//    }
//
//    @Override
//    public boolean onSingleTapUp(MotionEvent e) {
//        Log.i(TAG, "onSingleTapUp: ");
//        return false;
//    }
//
//    @Override
//    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//        Log.i(TAG, "onScroll: ");
//        return false;
//    }
//
//    @Override
//    public void onLongPress(MotionEvent e) {
//        Log.i(TAG, "onLongPress: ");
//    }
//
//    @Override
//    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//        Log.i(TAG, "onFling: ");
//        return false;
//    }



    private class  test extends GestureDetector.SimpleOnGestureListener{
        public test() {
            super();
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.i(TAG, "onSingleTapUp: ");
            return super.onSingleTapUp(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Log.i(TAG, "onLongPress: ");
            super.onLongPress(e);
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.i(TAG, "onScroll: ");
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.i(TAG, "onFling: ");
            return super.onFling(e1, e2, velocityX, velocityY);
        }

        @Override
        public void onShowPress(MotionEvent e) {
            Log.i(TAG, "onShowPress: ");
            super.onShowPress(e);
        }

        @Override
        public boolean onDown(MotionEvent e) {
            Log.i(TAG, "onDown: ");
            return super.onDown(e);
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.i(TAG, "onDoubleTap: ");
            return super.onDoubleTap(e);
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            Log.i(TAG, "onDoubleTapEvent: ");
            return super.onDoubleTapEvent(e);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.i(TAG, "onSingleTapConfirmed: ");
            return super.onSingleTapConfirmed(e);
        }

        @Override
        public boolean onContextClick(MotionEvent e) {
            Log.i(TAG, "onContextClick: ");
            return super.onContextClick(e);
        }
    }

    private class text2 implements ScaleGestureDetector.OnScaleGestureListener{

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            return false;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            return false;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {

        }
    }
}
