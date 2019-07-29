package com.feyon.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.feyon.myapplication.glide.GlideApp;

public class Main49Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main49);
        ViewGroup viewGroup=findViewById(R.id.parentView);
        for (int i = 0; i <5 ; i++) {
            ImageView image=new ImageView(this);
            image.setLayoutParams(new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            viewGroup.addView(image);
            GlideApp.with(Main49Activity.this).load("http://119.29.17.109/test/Uploads/Photo/20190617/5d0734ea14b84.jpg").into(image);
        }

    }
}
