package com.feyon.myapplication;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by DS on 2018/6/19.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@myAnnotation2(listenerType= View.OnClickListener.class,listenerSetter="setOnClickListener",methodName="onClick")
public @interface myAnnotation22 {
    int[] value();
}
