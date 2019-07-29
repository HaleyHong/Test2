package com.feyon.myapplication;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by DS on 2018/6/19.
 */

public class DynameicHandler implements InvocationHandler {

    private WeakReference<Object> handlerRef;
    private final HashMap<String,Method>methodHashMap=new HashMap<String, Method>();

    public DynameicHandler(Object o) {
        this.handlerRef=new WeakReference<Object>(o);
    }

    public void addMethod(String name,Method method){
        methodHashMap.put(name,method);
    }

    public Object getHandler(){
        return handlerRef.get();
    }

    public void setHandlerRef(Object handlerRef)
    {
        this.handlerRef=new WeakReference<Object>(handlerRef);
    }


    //每次btn点击时都会调用这个方法  每次点击时都会执行OnClickListener.OnClick这个方法，这应该就是代理了
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object handler=handlerRef.get();
        if (handler!=null)
        {
            String methodName=method.getName();
            method=methodHashMap.get(methodName);
            if (method!=null)
            {
                return method.invoke(handler,args);
            }
        }
        return null;
    }
}
