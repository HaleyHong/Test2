package com.feyon.myapplication.Service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.feyon.myapplication.Main36Activity;
import com.feyon.myapplication.R;

public class MyService extends Service {

    private String TAG="service";
    private MyBind myBind=new MyBind();
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ");
        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Intent notificationIntent=new Intent(this,Main36Activity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,notificationIntent,0);

        Notification.Builder builder=new Notification.Builder(this);
        builder.setContentText("sss");
        builder.setContentTitle("title");
        builder.setSmallIcon(R.drawable.jpush_notification_icon);
        builder.setContentIntent(pendingIntent);

        Notification notification=builder.build();

        startForeground(0,notification);
    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        Log.i(TAG, "onBind: ");
        return myBind;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind: ");


        return super.onUnbind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

//        String s=intent.getStringExtra("tag");
//        Log.i(TAG, "onStartCommand: "+s);


        return super.onStartCommand(intent, flags, startId);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.i(TAG, "onDestroy: ");
    }


    public class MyBind extends Binder {

    }

}
