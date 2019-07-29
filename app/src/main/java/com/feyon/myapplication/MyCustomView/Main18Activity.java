package com.feyon.myapplication.MyCustomView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.feyon.myapplication.R;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main18Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main18);

        ExecutorService executorService=new ThreadPoolExecutor(1,1,0,TimeUnit.MINUTES,new LinkedBlockingDeque<Runnable>(),Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
        Future<String> future= executorService.submit(new Callable<String>() {

            @Override
            public String call() throws Exception {
                Log.i("TAGs", "ss: ");
                Thread.sleep(3000);
                return "hello";
            }
        });

        try {
            String s=(String)future.get();
            System.out.println("sss");
            Log.i("TAGs", "onCreate: ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

//        TabLayout.Tab s=tabLayout.newTab().setCustomView(v);
//        tabLayout.addTab(s);

        TagFlowLayout flowLayout=findViewById(R.id.id_flowlayout);

        View v =LayoutInflater.from(this).inflate(R.layout.item_text,null);
        flowLayout.addView(v);
        View v2 =LayoutInflater.from(this).inflate(R.layout.item_text,null);
        flowLayout.addView(v2);
        View v3 =LayoutInflater.from(this).inflate(R.layout.item_text,null);
        flowLayout.addView(v3);
        View v4 =LayoutInflater.from(this).inflate(R.layout.item_text,null);
        flowLayout.addView(v4);
        View v5 =LayoutInflater.from(this).inflate(R.layout.item_text,null);
        flowLayout.addView(v5);
//
//        findViewById(R.id.check).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Beta.checkUpgrade(true,true);
//
//            }
//        });
//
//        findViewById(R.id.down).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Beta.downloadPatch();
//            }
//        });
//
//        findViewById(R.id.apply).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Beta.applyDownloadedPatch();
//            }
//        });


       /* final CTextView cTextView=findViewById(R.id.my_text);

        cTextView.setCText("哈哈哈哈哈哈哈哈哈hhhhhh啊");

        cTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cTextView.start();

            }
        });*/


     /*  final CCyclerView cCyclerView=findViewById(R.id.my_text);
       cCyclerView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               cCyclerView.start();
           }
       });*/


       /* final CView1 view1=findViewById(R.id.my_text);

        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view1.start();
            }
        });*/

       final CViewProgress2 progress2=findViewById(R.id.my_text);
        progress2.setScore(179+"",200+"");
        progress2.setAnimatorLoading(new CViewProgress2.AnimatorLoading() {
            @Override
            public void animatorLoadingCur(String cur) {
            }
        });
        progress2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               progress2.start();
           }
       });


    }

    @SuppressLint("MissingPermission")
    private void init() {

        if (IsForeground(this) == false) {
            ActivityManager am = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
            am.moveTaskToFront(getTaskId(), ActivityManager.MOVE_TASK_WITH_HOME);
        }


    }

    public class myhandler extends Handler{
        public myhandler(Activity activity) {
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }

    public boolean IsForeground(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (tasks != null && !tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }
}
