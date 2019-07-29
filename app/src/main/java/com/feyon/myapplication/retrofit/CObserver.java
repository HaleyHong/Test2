package com.feyon.myapplication.retrofit;

import android.util.Log;
import android.widget.Toast;

import com.feyon.myapplication.ExampleApplication;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;


/**
 * Created by DS on 2018/6/29.
 */

public abstract class CObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {

        if (t instanceof HttpResult){
            if (((HttpResult)t).isCodeInvalid()){
                onError(new ApiError(1,"2"));
            }
        }
        OnResult(t);
    }

    @Override
    public void onError(Throwable e) {
        Log.w("Subscriber onError", e);
        if (e instanceof HttpException) {
            System.out.println("");
            // We had non-2XX http error
//            Toast.makeText(mContext, mContext.getString(R.string.server_internal_error), Toast.LENGTH_SHORT).show();
        }else if (e instanceof IOException) {
            System.out.println("");
            // A network or conversion error happened
//            Toast.makeText(mContext, mContext.getString(R.string.cannot_connected_server), Toast.LENGTH_SHORT).show();
        }else if (e instanceof ApiError)
        {
            Toast.makeText(ExampleApplication.getMYContext(),"请求失败",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onComplete() {

    }

     abstract void OnResult(T t);
}
