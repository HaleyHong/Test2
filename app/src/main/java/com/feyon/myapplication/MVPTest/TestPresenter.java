package com.feyon.myapplication.MVPTest;

import com.feyon.myapplication.MVPBase.BaseCallBackImp;
import com.feyon.myapplication.MVPBase.BasePresenter;

/**
 * Created by DS on 2018/4/18.
 */

public class TestPresenter extends BasePresenter{



    @Override
    public void getData() {
        TestMode.getData(new BaseCallBackImp<ModBean>() {


            @Override
            public void onSuccess(ModBean data) {

            }

            @Override
            public void onFail(String msg) {

            }

            @Override
            public void onComplete(String msg) {

            }
        });
    }



}
