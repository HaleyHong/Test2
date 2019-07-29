package com.feyon.myapplication;

import android.app.Activity;
import android.view.View;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by DS on 2018/6/19.
 */

public class testAnnotation {

    private static final String METHOD_FIND_VIEW_BY_ID="findViewById";

    public static void injectContentVIew(Activity activity)
    {
        Class<? extends Activity> myClass=activity.getClass();
        //获取类上面的注解
       myAnnotation c= myClass.getAnnotation(myAnnotation.class);

       if (c!=null)
       {
           int contentViewLayoutId=c.value();
           try {
               Method method=myClass.getMethod("setContentView", int.class);
               method.setAccessible(true);
               method.invoke(activity,contentViewLayoutId);
           }catch (Exception e)
           {
               e.printStackTrace();
           }
       }
    }

    public static void inJectViews(Activity activity){
        Class<? extends Activity> myclass=activity.getClass();
        //获取成员变量
        Field[] fields= myclass.getDeclaredFields();

        for (Field field: fields) {
            //获取成员变量上的指定的注解啊
            myAnnotation annotation=field.getAnnotation(myAnnotation.class);

            if (annotation!=null)
            {
                int viewid=annotation.value();
                if (viewid!=-1)
                {
                    try {
                        //获取方法
                        Method method=myclass.getMethod(METHOD_FIND_VIEW_BY_ID,int.class);
                        //设置并获取对象
                        Object resView= method.invoke(activity,viewid);
                        //赋值给成员变量
                        field.setAccessible(true);
                        field.set(activity,resView);
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    public static void inJectEnvents(Activity activity)
    {
        Class<? extends Activity> myclass=activity.getClass();
        //获取所有的方法
        Method[] methods=myclass.getMethods();

        for (Method method:methods)
        {
            //获取方法上的所以注解
            Annotation[] annotations=method.getAnnotations();
//            myAnnotation22 sd= method.getAnnotation(myAnnotation22.class);
//
//            myAnnotation2 sd2= method.getAnnotation(myAnnotation2.class);
            for (Annotation annotation:annotations)
            {
                //获取anntation的类
                Class<? extends Annotation>  annotationType=annotation.annotationType();
                //获取指定的类别
                myAnnotation2 my2=annotationType.getAnnotation(com.feyon.myapplication.myAnnotation2.class);


                if(my2!=null)
                {
                    //获取anntation的所有成员变量
                    String listenerSetter=my2.listenerSetter();
                    Class<?> listenerType=my2.listenerType();
                    String methodName=my2.methodName();

                    try{
                        //
                        Method amethod=annotation.annotationType().getDeclaredMethod("value");

                        int[] viewIds= (int[]) amethod.invoke(annotation);
                        //代理
                        DynameicHandler handler=new DynameicHandler(activity);
                        //onclick 和实现的方法名
                        handler.addMethod(methodName,method);
                        //构造实例
                        Object listener= Proxy.newProxyInstance(listenerType.getClassLoader(),new Class[]{listenerType},handler);


                        for (int viewid:viewIds)
                        {
                            Class<?> myclass2= activity.getClass();
                            //获取方法
                            Method method1 = myclass2.getMethod(METHOD_FIND_VIEW_BY_ID,int.class);
                            //使用方法 并获取对象 acticvity.findViewById(viewid)
                            View view= (View) method1.invoke(activity,viewid);

                            //获取setOnClickListener方法 ；setOnClickListener，View.OnLickListener.class
                            Method setEventListener=view.getClass().getMethod(listenerSetter,listenerType);
                            //实现方法
                            setEventListener.invoke(view,listener);
                        }

                    }catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }
}
