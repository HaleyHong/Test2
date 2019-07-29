package com.feyon.myapplication.ok_re_rx;

import com.feyon.myapplication.ExampleApplication;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by DS on 2018/7/5.
 */

public class NewWorkInstance {

    private Retrofit retrofit;
    private OkHttpClient okHttpClient;
    private Cache cache;

    private NewWorkInstance() {
        File file=ExampleApplication.getMYContext().getCacheDir();
        cache=new Cache(file,4*1024*1024);
        okHttpClient=new OkHttpClient.Builder().cache(cache).build();

        retrofit=new Retrofit.Builder().client(okHttpClient).baseUrl("http://test.10000s.com/").addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).build();
    }

    private static class inner{
        private static final NewWorkInstance n=new NewWorkInstance();
    }

    public static NewWorkInstance getInstance(){
        return inner.n;
    }

    public static Retrofit getRetrofit() {
        return getInstance().retrofit;
    }

    public static OkHttpClient getOkHttpClient() {
        return getInstance().okHttpClient;
    }

    public static Cache getCache() {
        return getInstance().cache;
    }
}
