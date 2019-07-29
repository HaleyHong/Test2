package com.feyon.myapplication.retrofit.customGson;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by DS on 2018/6/29.
 */

public class MyFunction implements Function<Observable<? extends Throwable>, Observable<?>> {
    @Override
    public Observable<?> apply(Observable<? extends Throwable> observable) throws Exception {
        return observable.timer(1, TimeUnit.MILLISECONDS);
    }
}
