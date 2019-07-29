package com.feyon.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import java.util.*

class Main41Activity : AppCompatActivity(), View.OnClickListener {

    var str:String="s"
    get()="ss"
    set(value) {field=value};

    override fun onClick(p0: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main41)
        var v=findViewById<View>(R.id.testKoltin)
//        v.setOnClickListener(this);


        v.setOnClickListener(this)

        hell(3);

        var sez=HashSet<String>()
        var s= emptyList<Int>()
        var ss= LinkedList<Int>()
        var sss= mutableMapOf(1 to "s")


        var e= test("s");
        println(str+"tag")

        println(
        )

        when(0)
        {
            in 0..9->println()
            else -> println();
        }

        var b:TestKotlinBean=TestKotlinBean(2);

        var c:TestKotlinBean2= TestKotlinBean2(2)

        b.s
    }
    public  fun   hell(a:Int):Int{
        var s:Int=3;
        var ss:Char= s.toChar();
        for (i in 0..9)
        {
            print(i);
        }
        print(ss)
        return 3;
    }

    public fun test(obg:Any):Int? {
        if (obg is String)
        {
            return obg.length;
        }
        var a = 1
        var b = 2
        a = b.also { b = a };

        return null;
    }
}
