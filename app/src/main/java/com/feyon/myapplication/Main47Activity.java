package com.feyon.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.feyon.myapplication.glide.GlideApp;

public class Main47Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main47);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent();
//                intent.setClass(Main47Activity.this,Main46Activity.class);
//                startActivity(intent);
//                Intent intent=new Intent();
//                intent.setClass(Main47Activity.this,LibMainActivity.class);
//                startActivity(intent);

                ImageView icon=findViewById(R.id.image);
                GlideApp.with(Main47Activity.this).load("http://www.10000s.com/servlet/ValidateCodeServlet").into(icon);
            }
        });



    }

    public static void main(String...args){
        System.out.println("s");
    }
}
