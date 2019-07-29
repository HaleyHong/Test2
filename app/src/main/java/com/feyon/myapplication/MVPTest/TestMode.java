package com.feyon.myapplication.MVPTest;

import com.feyon.myapplication.MVPBase.BaseCallBackImp;

/**
 * Created by DS on 2018/4/18.
 */

public class TestMode {


    public static   void getData(final BaseCallBackImp<ModBean> baseCallBackImp) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);

                    baseCallBackImp.onSuccess(null);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
