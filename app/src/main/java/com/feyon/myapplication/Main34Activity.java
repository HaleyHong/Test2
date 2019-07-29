package com.feyon.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Main34Activity extends AppCompatActivity {

    private static  String TAG="executor";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main34);

//        test3();

        final BoundedBuffer boundedBuffer=new BoundedBuffer();

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                boundedBuffer.put("1");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

            }
        });



        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            boundedBuffer.take();


                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }


    public void test(){
        final ExecutorService service=Executors.newFixedThreadPool(10);
        final Semaphore semaphore=new Semaphore(10);
        for (int i = 0; i <100 ; i++) {
            final int finalI = i;
            Runnable runnable=new Runnable() {
                @Override
                public void run() {
                    Log.i(TAG, "run: "+ finalI);

                    try {
                        semaphore.acquire();
                        Thread.sleep(2000);
                        Log.i(TAG, "run: start"+finalI);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        semaphore.release();
                    }
                }
            };

            service.submit(runnable);
        }

    }

    public void test2(){
        MyAsyncTask myAsyncTask=new MyAsyncTask();
        myAsyncTask.execute(100);

        myAsyncTask.onCancelled();

        myAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,100);
    }

    public void test3(){
        final Ba ba=new Ba();
        new Thread(new Runnable() {
            @Override
            public void run() {
                ba.start("sub");
            }
        }).start();


//        ba.start("main");
//
        new Thread(new Runnable() {
            @Override
            public void run() {
                ba.start("main");
            }
        }).start();

//
    }

    public class  MyAsyncTask extends AsyncTask<Integer,Integer,String>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Integer... integers) {
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }


    }


    public class  Ba{

        ReentrantLock reentrantLock=new ReentrantLock();
        Condition condition=reentrantLock.newCondition();


        boolean aBoolean=true;

        public void  start(String s)
        {
            if (s.equals("sub"))
            {
                sub();
            }else {
                main();
            }
        }

        public void main()
        {
            reentrantLock.lock();

            while (aBoolean)
            {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (int i = 0; i < 10; i++) {
                Log.i(TAG, "main: ");
            }

            aBoolean=true;
            condition.signal();
            reentrantLock.unlock();
        }

        public void sub()
        {
            reentrantLock.lock();
            while (!aBoolean)
            {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (int i = 0; i <10 ; i++) {
                Log.i(TAG, "sub: ");
            }
            aBoolean=false;
            condition.signal();
            reentrantLock.unlock();
        }

    }


    public class  MyList{
        private String[] items={};
        private ReentrantLock reentrantLock=new ReentrantLock();
        private Condition writeCondition=reentrantLock.newCondition();
        private Condition readCondition=reentrantLock.newCondition();
        private int length=0;

        public String read(){
            reentrantLock.lock();
            try {
                if (length==0)
                {

                }

            }catch (Exception e){

            }

            reentrantLock.unlock();

            return "";
        }
    }


    class BoundedBuffer {
        final ReentrantLock lock = new ReentrantLock();//锁对象
        final Condition notFull  = lock.newCondition();//写线程条件
        final Condition notEmpty = lock.newCondition();//读线程条件

        final Object[] items = new Object[100];//缓存队列
        int putptr/*写索引*/, takeptr/*读索引*/, count/*队列中存在的数据个数*/;

        public void put(Object x) throws InterruptedException {
            lock.lock();
            try {
                while (count == items.length)//如果队列满了
                    notFull.await();//阻塞写线程
                items[putptr] = x;//赋值
                if (++putptr == items.length) putptr = 0;//如果写索引写到队列的最后一个位置了，那么置为0
                ++count;//个数++
                notEmpty.signal();//唤醒读线程
            } finally {
                lock.unlock();
            }
        }

        public Object take() throws InterruptedException {
            lock.lock();
            try {
                while (count == 0)//如果队列为空
                    notEmpty.await();//阻塞读线程


                Object x = items[takeptr];//取值
                if (++takeptr == items.length) takeptr = 0;//如果读索引读到队列的最后一个位置了，那么置为0
                --count;//个数--
                notFull.signal();//唤醒写线程
                return x;
            } finally {
                lock.unlock();
            }
        }
    }
}
