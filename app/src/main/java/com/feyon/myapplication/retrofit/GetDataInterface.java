package com.feyon.myapplication.retrofit;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by DS on 2018/6/27.
 */

public interface GetDataInterface {

    @GET("outer/homePageInterFace.spring")
    Call<ResponseBody> getDataType1(@Query("method") String s, @Query("isNew") String num);

    @POST("outer/homePageInterFace.spring")
    @FormUrlEncoded
    Call<ResponseBody> getDataType2(@Field("method") String s,@Field("isNew") String num);


    @POST("outer/homePageInterFace.spring")
    @FormUrlEncoded
    Call<ResponseBody> getDataType3(@FieldMap Map<String,String> map);

    //不知道为什么用@body接收不了
//    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("outer/homePageInterFace.spring")
    Call<ResponseBody> getDataType4(@Body RequestBody s);


    @GET("/cfapp/app.spring?method=appGetTicketList&userCode=18146612901&pw=1104959D53DC3B60F2D40CD4A47D79E7&versionDate=2018-03-29&jpushRegId=A_010e926ad33&checkLoginCode=20180428142204")
    Observable<HttpResult<List<DataBean1>>> getDataType11();
}
