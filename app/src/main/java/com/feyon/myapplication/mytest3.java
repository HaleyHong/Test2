package com.feyon.myapplication;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by DS on 2018/7/18.
 */

public class mytest3 implements InvocationHandler{

    private Object object;
    public Object newInstance(Object o){
        this.object=o;
        return Proxy.newProxyInstance(o.getClass().getClassLoader(),o.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object re;
         re=   method.invoke(proxy,args);


        return re;
    }
}
