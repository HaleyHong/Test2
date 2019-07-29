package com.feyon.myapplication.cropImage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.feyon.myapplication.R;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class Main33Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main33);


//

        getReferer("https://wx.tenpay.com/cgi-bin/mmpayweb-bin/checkmweb?prepay_id=wx24151350782855b5ebb694843152502417&package=2837133232&redirect_url=https%3A%2F%2Ftmallapi.bluemoon.com.cn%2Fwash%2FwxpayModal");

        int a=12;
        int b=a&(-a);
        System.out.println();
    }

    private String getReferer(String url)
    {
        try {
            url=URLDecoder.decode(url,   "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String mark="redirect_url=";
        int index=url.indexOf(mark);

        if (index<0)
            return "";

        String a=url.substring(index+mark.length(),url.length());

        String h="";
        if (a.startsWith("https://"))
        {
            h="https://";
        }else if (a.startsWith("http://"))
        {
            h="http://";
        }else {
            return "";
        }

        a=a.replace(h,"");

        if (a.contains("/"));
        {
            int i=a.indexOf("/");
            a= a.substring(0,i);
            a=h+a;
            System.out.println("");
        }
        return a;
    }

    public void on(View view)
    {
        CameraImageUtil.openLocalImage(this);
    }

    public void on2(View view)
    {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode!=RESULT_OK)
            return;

        if (requestCode== CameraImageUtil.GET_IMAGE_FROM_PHONE)
        {
            String s= data.getData().getPath();

            ImageView img=findViewById(R.id.img);
            Glide.with(Main33Activity.this).load(FileUtil.getPathFromUri(this,data.getData())).into(img);
        }
    }
}
