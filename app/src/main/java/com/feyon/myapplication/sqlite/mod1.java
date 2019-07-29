package com.feyon.myapplication.sqlite;

import org.litepal.crud.LitePalSupport;

/**
 * Created by DS on 2018/7/24.
 */

public class mod1 extends LitePalSupport{
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
