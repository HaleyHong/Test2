package com.feyon.myapplication.glide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;

import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.feyon.myapplication.R;
import com.feyon.myapplication.largeImage.MyView;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Main24Activity extends AppCompatActivity {

    private String url="http://test.10000s.com//b2cupload/top/advertimg/20180717173239171.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main24);

        String s="ss,ss";

        String[] sss=s.split(",");
        if (sss!=null)
        {
            List arrayList=Arrays.asList(sss);
            System.out.println("ssss"+arrayList.size());
        }else {
            System.out.println("null");
        }

        init();
    }

    private void init(){



      new Thread(new Runnable() {
          @Override
          public void run() {
              File file= null;
              try {
                  file = GlideApp.with(Main24Activity.this).load(url).downsample(new DownsampleStrategy() {
                      @Override
                      public float getScaleFactor(int sourceWidth, int sourceHeight, int requestedWidth, int requestedHeight) {
                          return 0;
                      }

                      @Override
                      public SampleSizeRounding getSampleSizeRounding(int sourceWidth, int sourceHeight, int requestedWidth, int requestedHeight) {
                          return null;
                      }
                  }).getDownloadOnlyRequest().submit().get();
              } catch (InterruptedException e) {
                  e.printStackTrace();
              } catch (ExecutionException e) {
                  e.printStackTrace();
              }
              Log.i("TAG", "init: "+file.getPath());

              final File finalFile = file;

              Main24Activity.this.runOnUiThread(new Runnable() {
                  @Override
                  public void run() {
                      //此时已在主线程中，可以更新UI了
                      MyView myView=new MyView(Main24Activity.this,finalFile);
                      ViewGroup viewGroup= findViewById(R.id.my_frame);
                      viewGroup.addView(myView,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                  }
              });


          }
      }).start();


//        try {
//            File file = Glide.with(this)
//                    .load(url)
//                    .downloadOnly()
//                    .get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
    }
}
