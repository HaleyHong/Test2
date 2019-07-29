package com.feyon.myapplication.retrofit;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.feyon.myapplication.R;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main12Activity extends AppCompatActivity {
    private String TAG=getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main12);

        test();
    }

    private void test(){
        //GET
        GetDataInterface getDataInterface= retrofitInstance.getInstance().getRetrofit().create(GetDataInterface.class);
//        Call<ResponseBody> call1= getDataInterface.getDataType1("getAllHomePageData","2");
//        call1.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                String s =response.body().toString();
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//            }
//        });

        //Filed
//        Call<ResponseBody> call2=getDataInterface.getDataType2("getAllHomePageData","2");
//        call2.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                Log.i(TAG, "onResponse: "+response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//            }
//        });



        Map map=new HashMap();
        map.put("method","getAllHomePageData");
        map.put("isNew","2");

        Call<ResponseBody> call2=getDataInterface.getDataType3(map);
        call2.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.i(TAG, "onResponse: "+response.body().toString());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    /*    Call<ResponseBody> call= getDataInterface.getDataType1("11","");
        call.enqueue(new testCa<ResponseBody>() {
            @Override
            public void loadding(long tatal, long progress) {

            }

            @Override
            public void OnSuccess(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                super.onFailure(call, t);
            }
        });*/


        //不知道为什么用@body接收不了

//        BodyBean bodyBean=new BodyBean();
//        bodyBean.setIsNew("2");
//        bodyBean.setMethod("getAllHomePageData");
//
//        Gson gson=new Gson();
//        String ss=gson.toJson(bodyBean);
//        RequestBody requestBody= RequestBody.create(MediaType.parse("application/json; charset=utf-8"),ss);
//
//        Call<ResponseBody> call4=getDataInterface.getDataType4(requestBody);
//        call4.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                System.out.println("");
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//            }
//        });

        Observable<HttpResult<List<DataBean1>>> httpResultCall= getDataInterface.getDataType11();

//        httpResultCall.flatMap(new Function<HttpResult<List<DataBean1>>, Observable<List<DataBean1>>>() {
//            @Override
//            public Observable<List<DataBean1>> apply(HttpResult<List<DataBean1>> listHttpResult) throws Exception {
//                return Observable.fromArray(listHttpResult.getContent());
//            }
//        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Object>() {
//            @Override
//            public void accept(Object o) throws Exception {
//                System.out.println("");
//            }
//        });
       /* ArrayList arrayList=new ArrayList();
        arrayList.add("s");
        arrayList.add("ss");
        Observable.just("s",1,arrayList).map(new Function<Serializable, Serializable>() {
            @Override
            public Serializable apply(Serializable serializable) throws Exception {
                return null;
            }
        }).retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
                return null;
            }
        }).subscribe(new Observer<Serializable>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Serializable serializable) {
                System.out.println();

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });*/
    /*    httpResultCall.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CObserver<HttpResult<List<DataBean1>>>() {
            @Override
            void OnResult(HttpResult<List<DataBean1>> listHttpResult) {

            }

            @Override
            public void onSubscribe(Disposable d) {
                super.onSubscribe(d);
            }
        });*/


      /*  httpResultCall.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<HttpResult<List<DataBean1>>>() {
            @Override
            public void accept(HttpResult<List<DataBean1>> listHttpResult) throws Exception {
                System.out.println("");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println("");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("");
            }
        }, new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                //不知道为什么是null的
                System.out.println("");
            }
        });*/

/*
      Observable.just(1,2,3,4,5).scan(new BiFunction<Integer, Integer, Integer>() {
          @Override
          public Integer apply(Integer s, Integer s2) throws Exception {
              return s+s2;
          }
      }).subscribe(new Consumer<Integer>() {
          @Override
          public void accept(Integer s) throws Exception {
              Log.i(TAG, "accept: "+s);
          }
      });*/

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++)
        {
            list.add("hello i:" + i);
        }

        Observable.fromIterable(list).buffer(4).subscribe(new Observer<List<String>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<String> strings) {
                for (String s : strings)
                {
                    Log.i(TAG, s);
                }
                Log.i(TAG, "\n next group");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        MediaType mediaType=MediaType.parse("text/plain");
        MediaType mediaType1=MediaType.parse("application/octet=stream");

        RequestBody.create(mediaType,"s");

        MultipartBody.Part.createFormData("file","filename",RequestBody.create(mediaType1,new File(Environment.getExternalStorageDirectory()+"s")));




    }
}
