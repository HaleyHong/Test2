package com.feyon.myapplication.okhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.feyon.myapplication.R;

import java.io.IOException;
import java.util.List;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Main14Activity extends AppCompatActivity {
    Request request;
    Cache cache;
    OkHttpClient client;
    String API_HOST="http://test.10000s.com/outer/homePageInterFace.spring?method=getAllHomePageData&isNew=2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main14);
        cache=new Cache(getCacheDir(),4*1024*1024);
        client=new OkHttpClient.Builder().cache(cache).cookieJar(new CookieJar() {
            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
//                cookies.put(httpUrl.host(), list);
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                return null;
            }
        }).build();

        findViewById(R.id.onclick).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request=new Request.Builder().url(API_HOST).cacheControl(CacheControl.FORCE_CACHE).build();
                init();
            }
        });

        request=new Request.Builder().url(API_HOST).build();
        init();

    }

    private void init ()
    {



        Call call= client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                System.out.println();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful())
                {
                    String s=response.body().string();
                    System.out.println("");
                }
            }
        });

//        File file=new File(Environment.getExternalStorageDirectory(),"bababa.mp4");
//        RequestBody requestBody=RequestBody.create(MediaType.parse("application/octet-steam"),file);


    }
}

