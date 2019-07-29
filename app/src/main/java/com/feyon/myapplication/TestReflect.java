package com.feyon.myapplication;

import android.util.Log;

public class TestReflect {

    private String a="a";
    protected String b="b";
    public String c="c";

    static {
        Log.i("TestReflect", "static initializer: ");
    }

    public String sayStr(String sy)
    {
       return  sy+"finish";
    }
}
