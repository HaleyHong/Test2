package com.feyon.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.widget.RemoteViews;

import static android.app.Notification.DEFAULT_ALL;
import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;

/**
 * Created by DS on 2018/4/24.
 */

public class MyTestService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return  new myBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();


        init();
    }

    private void init(){


    }

    public class myBinder extends Binder{
        public void setProgress(int progress){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent=new Intent(MyTestService.this,Main2Activity.class);
                    intent.putExtra("sss","ss");
                    TaskStackBuilder taskStackBuilder=TaskStackBuilder.create(MyTestService.this);
                    taskStackBuilder.addParentStack(Main2Activity.class);
                    taskStackBuilder.addNextIntent(intent);

//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


                    PendingIntent pendingIntent=taskStackBuilder.getPendingIntent(0,FLAG_UPDATE_CURRENT);



                    NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    RemoteViews remoteView=new RemoteViews(getPackageName(),R.layout.item_text);
                    NotificationCompat.Builder builder=new NotificationCompat.Builder(MyTestService.this,"");
                    builder.setContentTitle("测试");
                    builder.setContentText("我是测试数据");
                    builder.setSmallIcon(R.drawable.ic_launcher);
                    builder.setAutoCancel(true);
                    builder.setDefaults(DEFAULT_ALL);
//                    builder.setContent(remoteView);
//                    builder.setTicker("have a message");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        builder.setPriority(Notification.PRIORITY_HIGH);


                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                        builder.setVisibility(VISIBILITY_PUBLIC);
                        builder.setFullScreenIntent(pendingIntent,true);
                    }
//                builder.setProgress(0,0,true);
//
//                    builder.setStyle(new NotificationCompat.BigTextStyle().bigText("大数据的大叔级"));
//                    builder.addAction(R.mipmap.ic_launcher,"按键",pendingIntent);


                    Notification notification1= builder.build();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                        notification1.bigContentView=remoteView;
                    }
//                    notification1.flags |=Notification.FLAG_NO_CLEAR;
                    notificationManager.notify(11,notification1);
                }
            },0);
//            Intent intent=new Intent(getApplicationContext(),Main4Activity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//
//
//            NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//            NotificationCompat.Builder builder=new NotificationCompat.Builder(MyTestService.this,"");
//            builder.setContentTitle("测试");
//            builder.setContentText("我是测试数据");
//            builder.setSmallIcon(R.mipmap.ic_launcher);
//            builder.setAutoCancel(true);
//            builder.setProgress(0,0,true);
//            builder.setStyle(new NotificationCompat.BigTextStyle().bigText("大数据的大叔级"));
//
//
//
//            Notification notification1= builder.build();
//
////        notificationManager.notify(11,notification1);
//
//            MyTestService.this.startForeground(11,notification1);
        }
    }
}
