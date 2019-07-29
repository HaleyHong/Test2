package com.feyon.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main39Activity extends AppCompatActivity {

    private final String TAG="Main39Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main39);
        //反射

        try {
            Class a= Class.forName("com.feyon.myapplication.TestReflect");

            Constructor constructor= a.getConstructor();
            TestReflect testReflect= (TestReflect) constructor.newInstance();

            try {
                Method method= a.getMethod("sayStr", String.class);
                String s= (String) method.invoke(testReflect,"test");

                Field field= a.getDeclaredField("a");
                field.setAccessible(true);
                field.set(testReflect,"asdasdasd");

                Log.i(TAG, "onCreate: "+field.get(testReflect));
                Log.i(TAG, "onCreate: "+s);

            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
