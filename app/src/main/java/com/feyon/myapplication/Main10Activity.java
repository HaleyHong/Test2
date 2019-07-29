package com.feyon.myapplication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Main10Activity extends AppCompatActivity {

    @myAnnotation(R.id.annotation_btn)
    Button button;
    private NotificationCompat.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10);

        testAnnotation.inJectViews(this);
        testAnnotation.inJectEnvents(this);
    }

    @myAnnotation22({R.id.annotation_btn})
    public void on(View view){
//        Intent intent=new Intent(this,Main2Activity.class);
//        intent.putExtra("sss","ss");
//        TaskStackBuilder taskStackBuilder=TaskStackBuilder.create(this);
//        taskStackBuilder.addParentStack(Main2Activity.class);
//        taskStackBuilder.addNextIntent(intent);
//
////                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//
//        PendingIntent pendingIntent=taskStackBuilder.getPendingIntent(0,FLAG_UPDATE_CURRENT);
//
//

//        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        RemoteViews remoteView=new RemoteViews(getPackageName(),R.layout.item_text);
//        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"");
//        builder.setContentTitle("测试");
//        builder.setContentText("我是测试数据");
//        builder.setSmallIcon(R.mipmap.ic_launcher);
//        builder.setAutoCancel(true);
//        builder.setDefaults(DEFAULT_ALL);
////                    builder.setContent(remoteView);
////                    builder.setTicker("have a message");
////        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
////            builder.setPriority(Notification.PRIORITY_HIGH);
////
////
////        }
////        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//////                        builder.setVisibility(VISIBILITY_PUBLIC);
////            builder.setFullScreenIntent(pendingIntent,true);
////        }
////                builder.setProgress(0,0,true);
////
////                    builder.setStyle(new NotificationCompat.BigTextStyle().bigText("大数据的大叔级"));
////                    builder.addAction(R.mipmap.ic_launcher,"按键",pendingIntent);
//
//
//        Notification notification1= builder.build();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
////                        notification1.bigContentView=remoteView;
//        }
////                    notification1.flags |=Notification.FLAG_NO_CLEAR;
//        notificationManager.notify(11,notification1);

        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        builder=new NotificationCompat.Builder(Main10Activity.this);
        builder.setContentTitle("测试");
        builder.setContentText("我是测试数据");
        builder.setBadgeIconType(R.drawable.ic_launcher);
        builder.setSmallIcon(R.drawable.ic_launcher);
        NotificationChannel mChannel = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // 用户可以看到的通知渠道的名字.
            CharSequence name = "智慧教育相册上传";
//         用户可以看到的通知渠道的描述
            String description = "智慧教育相册上传";
            // 通知渠道的id
            String id = "my_channel_01";
            mChannel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH);
            mChannel.setDescription(description);

//         最后在notificationmanager中创建该通知渠道 //
            notificationManager.createNotificationChannel(mChannel);
        }
        // 通知渠道的id
        String CHANNEL_ID = "my_channel_01";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // 为该通知设置一个id
            int notifyID = 1;
            builder.setChannelId(CHANNEL_ID);
        }

        builder.setProgress(100,50,false);
//        builder.setAutoCancel(true);
//        builder.setDefaults(DEFAULT_ALL);
//                    builder.setContent(remoteView);
//                    builder.setTicker("have a message");
//			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//				builder.setPriority(Notification.PRIORITY_HIGH);
//
//
//			}
//			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
////                        builder.setVisibility(VISIBILITY_PUBLIC);
//				builder.setFullScreenIntent(pendingIntent,true);
//			}
//                builder.setProgress(0,0,true);
//
//                    builder.setStyle(new NotificationCompat.BigTextStyle().bigText("大数据的大叔级"));
//                    builder.addAction(R.mipmap.ic_launcher,"按键",pendingIntent);


        notificationManager.notify(1,builder.build());
    }


    @myAnnotation22({R.id.annotation_btn2})
    public void on2(View view){
        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        builder.setProgress(100,80,false);
//        builder.setAutoCancel(true);
//        builder.setDefaults(DEFAULT_ALL);
//                    builder.setContent(remoteView);
//                    builder.setTicker("have a message");
//			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//				builder.setPriority(Notification.PRIORITY_HIGH);
//
//
//			}
//			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
////                        builder.setVisibility(VISIBILITY_PUBLIC);
//				builder.setFullScreenIntent(pendingIntent,true);
//			}
//                builder.setProgress(0,0,true);
//
//                    builder.setStyle(new NotificationCompat.BigTextStyle().bigText("大数据的大叔级"));
//                    builder.addAction(R.mipmap.ic_launcher,"按键",pendingIntent);


        notificationManager.notify(1,builder.build());
    }
}
