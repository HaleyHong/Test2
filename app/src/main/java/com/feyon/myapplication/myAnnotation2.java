package com.feyon.myapplication;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by DS on 2018/6/19.
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface myAnnotation2 {

    Class<?> listenerType();

    String listenerSetter();

    String methodName();
}
