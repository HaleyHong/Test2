package com.feyon.myapplication.MVPBase;

import android.content.Context;

/**
 * Created by DS on 2018/4/18.
 */

public interface BaseViewImp<T>{

    public void onShowLoading();

    public void onHideLoading();

    public void onSuccess(T data);

    public void onFail(String msg);

    public void onComplete(String msg);

    public Context onGetContext();
}
