package com.feyon.myapplication.MVPBase;

/**
 * Created by DS on 2018/4/18.
 */

public interface BaseCallBackImp<T> {

    public void onSuccess(T data);

    public void onFail(String msg);

    public void onComplete(String msg);

}

