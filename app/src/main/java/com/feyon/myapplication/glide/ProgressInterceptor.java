package com.feyon.myapplication.glide;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by DS on 2018/7/20.
 */

public class ProgressInterceptor implements Interceptor{

    private static HashMap<String,ProgressListener> hashMap=new HashMap<String,ProgressListener>();

    public static HashMap<String,ProgressListener> getHashMap() {
        return hashMap;
    }

    public static CookieCallBack cookieCallBack=null;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request= chain.request();
        Response response= chain.proceed(request);
        String url = request.url().toString();

        ResponseBody body = response.body();
        Headers headers=response.headers();

        //获取请求图片结果的cookie
        if (cookieCallBack!=null)
        {
            List<String> cookies= headers.values("Set-Cookie");
            cookieCallBack.GetCookies(cookies);
        }
        Response newResponse = response.newBuilder().body(new okResponseBody(url, body)).build();
        return newResponse;
    }

    public interface CookieCallBack{
        public void GetCookies(List<String> cookies);
    }
}
