package com.feyon.myapplication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.feyon.myapplication.Service.MyService;

public class Main36Activity extends AppCompatActivity {
    private String TAG="Main36Activity";
    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i(TAG, "onServiceConnected: ");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i(TAG, "onServiceDisconnected: ");
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main36);

        findViewById(R.id.start36).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(Main36Activity.this,MyService.class);
                stopService(intent);
            }
        });
        findViewById(R.id.bind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent=new Intent();
                intent.setClass(Main36Activity.this,MyService.class);
                intent.putExtra("tag","hello");
                bindService(intent,serviceConnection,BIND_AUTO_CREATE);
            }
        });

        findViewById(R.id.unbind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                final Intent intent=new Intent();
//                intent.setClass(Main36Activity.this,MyService.class);
//                unbindService(serviceConnection);
                NotificationManager mNotifyMgr =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//
//                Intent notificationIntent=new Intent(Main36Activity.this,Main36Activity.class);
//                PendingIntent pendingIntent=PendingIntent.getActivity(Main36Activity.this,0,notificationIntent,0);
//
//                Notification.Builder builder=new Notification.Builder(Main36Activity.this);
//                builder.setContentText("sss");
//                builder.setContentTitle("title");
//                builder.setSmallIcon(R.drawable.jpush_notification_icon);
//                builder.setContentIntent(pendingIntent);
//
//                Notification notification=builder.build();


                NotificationCompat.Builder builder=new NotificationCompat.Builder(Main36Activity.this);
                builder.setContentTitle("智慧教育相册上传");
                builder.setContentText("上传中");
                builder.setBadgeIconType(R.drawable.ic_launcher);
                builder.setSmallIcon(R.drawable.ic_launcher);
                builder.setAutoCancel(true);
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
                    mNotifyMgr.createNotificationChannel(mChannel);
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    // 通知渠道的id
                    String CHANNEL_ID = "my_channel_01";
                    builder.setChannelId(CHANNEL_ID);
                }

                builder.setProgress(100,0,false);
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                    startForeground(notificationId,builder.build());
//                }else {
//                    startForeground(notificationId,builder.getNotification());
//                }
             mNotifyMgr.notify(112,builder.build());

            }
        });

        findViewById(R.id.bind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                final Intent intent=new Intent();
//                intent.setClass(Main36Activity.this,MyService.class);
//                unbindService(serviceConnection);
                NotificationManager mNotifyMgr =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

               mNotifyMgr.cancel(112);

            }
        });

    }
}
