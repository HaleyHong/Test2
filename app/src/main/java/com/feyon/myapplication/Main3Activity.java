package com.feyon.myapplication;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class Main3Activity extends AppCompatActivity {

    private static String flag=Main3Activity.class.getSimpleName();
    private MyTestService.myBinder iBinder;
    private int i=1;
    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            Log.i(flag, "onServiceConnected: ");
            iBinder= (MyTestService.myBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
;    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        findViewById(R.id.onclick).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main3Activity.this,MyTestService.class);

               bindService(intent,serviceConnection,BIND_AUTO_CREATE);
            }
        });



        findViewById(R.id.onclick2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               iBinder.setProgress(++i);


            }
        });
//        KeyboardView keyboardView=findViewById(R.id.my_keyBoard_view);
//
//        keyboardView.setKeyboard(new Keyboard(this,R.xml.keyboard_number));
//
//        keyboardView.setOnKeyboardActionListener(new KeyboardView.OnKeyboardActionListener() {
//            @Override
//            public void onPress(int primaryCode) {
//
//            }
//
//            @Override
//            public void onRelease(int primaryCode) {
//
//            }
//
//            @Override
//            public void onKey(int primaryCode, int[] keyCodes) {
//
//            }
//
//            @Override
//            public void onText(CharSequence text) {
//
//            }
//
//            @Override
//            public void swipeLeft() {
//
//            }
//
//            @Override
//            public void swipeRight() {
//
//            }
//
//            @Override
//            public void swipeDown() {
//
//            }
//
//            @Override
//            public void swipeUp() {
//
//            }
//        });
    }
}
