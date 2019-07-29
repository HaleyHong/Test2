package com.feyon.myapplication;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Main32Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main32);

        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        int max= (int) (Runtime.getRuntime().maxMemory()/1024/1024);
        int memory= activityManager.getMemoryClass();
        int memoryMax= activityManager.getLargeMemoryClass();


        final ImageView test_img=findViewById(R.id.test_img);
        final String ss=System.currentTimeMillis()+"";

        findViewById(R.id.create).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File file=QRcodeUtil.generateFile(ss);
                Glide.with(Main32Activity.this).load(file).into(test_img);
            }
        });

        findViewById(R.id.clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QRcodeUtil.clearAllQRCode();
            }
        });
        String[] s={"s"};
        s[1]="s";


        ByteArrayInputStream inputStream=new ByteArrayInputStream(ss.getBytes());
        BufferedInputStream bufferedInputStream=new BufferedInputStream(inputStream);

        int i=0;
        byte[] bytes=new byte[1024];//每次读入1kb

        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(outputStream);



        try {
            while ((i=bufferedInputStream.read(bytes))!=-1)
            {
                bufferedOutputStream.write(bytes);
            }

            bufferedOutputStream.flush();


        } catch (IOException e) {
            e.printStackTrace();
        }finally {

            if (bufferedOutputStream!=null)
            {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bufferedInputStream!=null)
            {
                try {
                    bufferedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        try {
            FileOutputStream file=new FileOutputStream(new File(""));
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(file);
            objectOutputStream.flush();

            DataOutputStream dataOutputStream=new DataOutputStream(file);
            dataOutputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
