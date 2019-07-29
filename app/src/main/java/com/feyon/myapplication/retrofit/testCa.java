package com.feyon.myapplication.retrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by DS on 2018/7/2.
 */

public abstract class testCa<T> implements Callback<T> {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful())
        {
            OnSuccess(call,response);
        }else {
            onFailure(call,new Throwable(response.message()));
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {

    }

    public abstract void loadding(long tatal,long progress);

    public abstract void OnSuccess(Call<T> call,Response<T> response);
}
