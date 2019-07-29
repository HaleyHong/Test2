package com.feyon.myapplication.retrofit;

import com.feyon.myapplication.retrofit.customGson.CGsonConverterFactory;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;

/**
 * Created by DS on 2018/6/27.
 */

public class retrofitInstance {

    private Retrofit retrofit;
    private String Path="http://test.10000s.com/";

    private retrofitInstance() {
        retrofit=new Retrofit.Builder().baseUrl(Path).addConverterFactory(CGsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
    }

    private static class innerClass{
        private static retrofitInstance retrofitInstance=new retrofitInstance();
    }

    public static retrofitInstance getInstance(){

        return innerClass.retrofitInstance;
    }


    public Retrofit getRetrofit() {
        return retrofit;
    }
}
