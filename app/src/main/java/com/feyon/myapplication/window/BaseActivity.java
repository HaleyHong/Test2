package com.feyon.myapplication.window;

import android.content.Context;
import android.graphics.PixelFormat;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.feyon.myapplication.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class BaseActivity extends AppCompatActivity {

    private View mTipView;
    private WindowManager windowManager;
    private WindowManager.LayoutParams layoutParams;
    private boolean has;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main25);
        InitTipView();

        final ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cm.requestNetwork(new NetworkRequest.Builder().build(), new ConnectivityManager.NetworkCallback() {
                @Override
                public void onLost(Network network) {
                    super.onLost(network);
                    Log.i("NetWork", "onReceive: false");
                    ///网络不可用的情况下的方法
                }

                @Override
                public void onAvailable(Network network) {
                    super.onAvailable(network);
                    Log.i("NetWork", "onReceive: ture");
                    ///网络可用的情况下的方法
                }
            });
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
            if (NetWorkConnectStateReceiver.isConnected(this))
            {
                if (!has)
                {
                    Log.i("NetWorksss", "onReceive: ture");
                    has=true;
                    windowManager.addView(mTipView,layoutParams);
                }

            }else {
                if (has)
                {
                    Log.i("NetWorksss", "onReceive: false");
                    has=false;
                    windowManager.removeView(mTipView);
                }
            }

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(NetWorkEvent event) {
        if (event.isConnect)
        {
            windowManager.addView(mTipView,layoutParams);
        }else {
            windowManager.removeView(mTipView);
        }
    }

    private void InitTipView(){
        LayoutInflater inflater=getLayoutInflater();
        mTipView=inflater.inflate(R.layout.activity_bar,null);
        windowManager= (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        layoutParams=new WindowManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                ,ViewGroup.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION
                ,WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, PixelFormat.TRANSLUCENT);

        layoutParams.gravity= Gravity.TOP;
        layoutParams.x=0;
        layoutParams.y=0;

    }
}
