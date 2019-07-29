package com.feyon.myapplication.ok_re_rx;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Streaming;

/**
 * Created by DS on 2018/7/5.
 */

public interface GetImageUrl {

    @Streaming
    @GET("b2cupload/fuwu/uploadFiles/upFileSecondaryTrade/2018/06/21/trade_20180621161916070054.jpg")
    Observable<ResponseBody> getImage();

    @Streaming
    @GET("b2cupload/fuwu/uploadFiles/upFileSecondaryTrade/2018/06/21/trade_20180621161916070054.jpg")
    Observable<ResponseBody> getImage2(@Header("Range") String range);
//    "Range", "bytes=" + size + "-")
}
