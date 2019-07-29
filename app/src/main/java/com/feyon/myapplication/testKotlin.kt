package com.feyon.myapplication

import java.sql.DriverManager.println


/**
 * Created by DS on 2018/5/30.
 */
class testKotlin {
    init {
       var s:testKotlin2 = testKotlin2();
        s.s="2";

        var ss:hello= hello();
        ss.foo();
    }

    open class hello

    fun  hello.foo(){
        println("s");
    }


}