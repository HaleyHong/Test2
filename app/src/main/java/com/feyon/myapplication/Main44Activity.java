package com.feyon.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Main44Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main44);
     /*   final ImageView imageView=findViewById(R.id.image);
        String s=Environment.getExternalStorageDirectory().getPath()+ File.separator+"Pictures/1.jpg";
//        Tiny.BitmapCompressOptions options = new Tiny.BitmapCompressOptions();
//        options.height=1280;
//        options.width=800;
        //options.height = xxx;//some compression configuration.
        Tiny.FileCompressOptions options = new Tiny.FileCompressOptions();
        options.outfile=Environment.getExternalStorageDirectory().getPath()+ File.separator+"Pictures/2.jpg";
        Tiny.getInstance().source(s).asFile().withOptions(options).compress(new FileCallback() {
            @Override
            public void callback(boolean isSuccess, String outfile, Throwable t) {
                //return the compressed file path
            }
        });*/
    }


}
