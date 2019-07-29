package com.feyon.myapplication;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.feyon.myapplication.BroadcastReceiver.MyBroadcastReceiver;
import com.feyon.myapplication.Service.MyService;
import com.hydream.myapplication8_2_remote_service.IMyAidlInterface;


public class Main35Activity extends AppCompatActivity {


    private MyBroadcastReceiver myBroadcastReceiver;

    private String TAG="Main35Activity";
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

    private IMyAidlInterface iMyAidlInterface;

    private ServiceConnection remoteService=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            iMyAidlInterface=IMyAidlInterface.Stub.asInterface(iBinder);

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main35);


        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent=new Intent();
                intent.setClass(Main35Activity.this,MyService.class);
                intent.putExtra("tag","hello");

                startService(intent);

            }
        });

        findViewById(R.id.stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent=new Intent();
                intent.setClass(Main35Activity.this,MyService.class);

                stopService(intent);
            }
        });

        findViewById(R.id.bind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent=new Intent();
                intent.setClass(Main35Activity.this,MyService.class);
                intent.putExtra("tag","hello");
                bindService(intent,serviceConnection,BIND_AUTO_CREATE);
            }
        });

        findViewById(R.id.unbind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent=new Intent();
                intent.setClass(Main35Activity.this,MyService.class);
                unbindService(serviceConnection);
            }
        });

        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(Main35Activity.this,Main36Activity.class);
                startActivity(intent);

            }
        });

        findViewById(R.id.remoteService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent("com.hydream.myapplication8_2_remote_service.IMyAidlInterface");
                intent.setPackage("com.hydream.myapplication8_2_remote_service");
                bindService(intent,remoteService,BIND_AUTO_CREATE);
            }
        });

        findViewById(R.id.remoteService_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iMyAidlInterface!=null)
                {
                    try {
                        iMyAidlInterface.myText("hello");
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
//        sendBroadcast();



    }




    @Override
    protected void onResume() {
        super.onResume();

//        myBroadcastReceiver=new MyBroadcastReceiver();
//
//        IntentFilter intentFilter=new IntentFilter();
////        intentFilter.addAction();
//        registerReceiver(myBroadcastReceiver,intentFilter);
//
//
//        LocalBroadcastManager localBroadcastManager=LocalBroadcastManager.getInstance(this);
//        localBroadcastManager.registerReceiver(myBroadcastReceiver,intentFilter);
//
//        localBroadcastManager.unregisterReceiver(myBroadcastReceiver);
//
//
//        localBroadcastManager.sendBroadcast(new Intent());
    }


    @Override
    protected void onPause() {
        super.onPause();

//        unregisterReceiver(myBroadcastReceiver);
    }
}
