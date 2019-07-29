package com.feyon.myapplication.largeImage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by DS on 2018/7/26.
 */

public class MyView extends View implements GestureDetector.OnGestureListener {
    private static final String TAG = "LargeImageView";

    private BitmapRegionDecoder mDecoder;

    //绘制的区域
    private volatile Rect mRect = new Rect();

    private int mScaledTouchSlop;

    // 分别记录上次滑动的坐标
    private int mLastX = 0;
    private int mLastY = 0;

    //图片的宽度和高度
    private int mImageWidth, mImageHeight;
    //手势控制器
    private GestureDetector mGestureDetector;
    private BitmapFactory.Options options;

    private File file;



    public MyView(Context context,File file) {
        super(context);
        this.file=file;
        init(context, null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;

    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {

        if (file==null)
            return;

        //设置显示图片的参数，如果对图片质量有要求，就选择ARGB_8888模式
        options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;


        mScaledTouchSlop = ViewConfiguration.get(getContext())
                .getScaledTouchSlop();
        Log.d(TAG, "sts:" + mScaledTouchSlop);
        //初始化手势控制器
        mGestureDetector = new GestureDetector(context, this);

        //获取图片的宽高
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            //初始化BitmapRegionDecode，并用它来显示图片
            mDecoder = BitmapRegionDecoder
                    .newInstance(file.getPath(), false);
            BitmapFactory.Options tmpOptions = new BitmapFactory.Options();
            // 设置为true则只获取图片的宽高等信息，不加载进内存
            tmpOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(is, null, tmpOptions);
            mImageWidth = tmpOptions.outWidth;
            mImageHeight = tmpOptions.outHeight;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //把触摸事件交给手势控制器处理
        if (mGestureDetector!=null)
        {
            return mGestureDetector.onTouchEvent(event);
        } else {
            return super.onTouchEvent(event);
        }
    }

    @Override
    public boolean onDown(MotionEvent e) {

        mLastX = (int) e.getRawX();
        mLastY = (int) e.getRawY();
        return true;
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
        int x = (int) e2.getRawX();
        int y = (int) e2.getRawY();
        move(x, y);

        return true;
    }

    /**
     * 移动的时候更新图片显示的区域
     *
     * @param x
     * @param y
     */
    private void move(int x, int y) {
        int deltaX = x - mLastX;
        int deltaY = y - mLastY;
        Log.d(TAG, "move, deltaX:" + deltaX + " deltaY:" + deltaY);
        //如果图片宽度大于屏幕宽度
        if (mImageWidth > getWidth()) {
            //移动rect区域
            mRect.offset(-deltaX, 0);
            //检查是否到达图片最右端
            if (mRect.right > mImageWidth) {
                mRect.right = mImageWidth;
                mRect.left = mImageWidth - getWidth();
            }

            //检查左端
            if (mRect.left < 0) {
                mRect.left = 0;
                mRect.right = getWidth();
            }

            invalidate();
        }
        //如果图片高度大于屏幕高度
        if (mImageHeight > getHeight()) {
            mRect.offset(0, -deltaY);

            //是否到达最底部
            if (mRect.bottom > mImageHeight) {
                mRect.bottom = mImageHeight;
                mRect.top = mImageHeight - getHeight();
            }

            if (mRect.top < 0) {
                mRect.top = 0;
                mRect.bottom = getHeight();
            }
            //重绘
            invalidate();
        }


        mLastX = x;
        mLastY = y;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        mLastX = (int) e.getRawX();
        mLastY = (int) e.getRawY();
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        int x = (int) e2.getRawX();
        int y = (int) e2.getRawY();
        move(x, y);
        return true;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        //显示图片
        if (file != null)
        {
            Bitmap bm = mDecoder.decodeRegion(mRect, options);
            canvas.drawBitmap(bm, 0, 0, null);
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        int imageWidth = mImageWidth;
        int imageHeight = mImageHeight;
        if (file==null)
            return;
        //默认显示图片的中心区域，开发者可自行选择
        mRect.left = imageWidth / 2 - width / 2;
        mRect.top = imageHeight / 2 - height / 2;
        mRect.right = mRect.left + width;
        mRect.bottom = mRect.top + height;

    }

}

