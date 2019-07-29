package com.feyon.myapplication.glide;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.feyon.myapplication.R;

public class Main19Activity extends AppCompatActivity {

    private ImageView imageView;
    private String url="http://p1.pstatp.com/large/166200019850062839d3";
    int i=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main19);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        mToolbar.setTitle("Kevin");//customize the title,个性化设置title
        mToolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));//设置title颜色
        mToolbar.setPadding(0,getStatusBarHeight(),0,0);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//show back button and make it enabled
        final DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        imageView=findViewById(R.id.image);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                inti();
                mDrawerLayout.openDrawer(Gravity.START);
            }
        });



        addStatusViewWithColor(this, ContextCompat.getColor(this,R.color.red));




    }

    private void inti(){
      Glide.with(this).asGif().load("http://www.10000s.com//b2cupload/top/advertimg/20150420094955007.jpg")
                .into(imageView);

        ProgressInterceptor.getHashMap().put(url, new ProgressListener() {
            @Override
            public void pro(long total, long cur) {
                Log.i("TAG", "t "+total +"c "+cur);

            }
        });

      GlideApp.with(this)
              .asGif()
              .load(url)


              .transform(new CircleCrop())

              .into(imageView);


    }

    private void addStatusViewWithColor(Activity activity, int color) {


//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//
//            Window window = getWindow();
////            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.setStatusBarColor(color);
////
//        } else {
//            ViewGroup rootView = (ViewGroup) activity.getWindow().getDecorView().findViewById(android.R.id.content);
//            rootView.setPadding(0, getStatusBarHeight(), 0, 0);
//            //根布局添加占位状态栏
//            ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
//            View statusBarView = new View(activity);
//            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                    getStatusBarHeight());
//            statusBarView.setBackgroundColor(color);
//            decorView.addView(statusBarView,0, lp);
//        }


    }

    public int getStatusBarHeight() {
        int result = 0;
        //获取状态栏高度的资源id
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }




//    private SimpleTarget<Drawable> simpleTarget=new SimpleTarget<Drawable>() {
//        @Override
//        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
//            imageView.setImageDrawable(resource);
//        }
//    };
}
