package com.feyon.myapplication.AudioRecord

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.feyon.myapplication.HelloY


import com.feyon.myapplication.R

class Main50Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main50)

        var text:TextView=findViewById(R.id.hello);

        val sa:String=android.os.Build.SERIAL;
        println("")

        var bean:Grass= Grass("s");
        bean.get();

        testVararg("1","2","3","4");

        var sum={a:Int,b:Int->
            var c:Int=a+b;
            println(c);
        };

        var sum1={
            var a:String?="sum";
            a;

        };

        test1(sum1);

        lamebda1();

        var b:String="1";

        var a:String="""
            TEST
            hello;
            hello;
            $b
        """;

        text.setText(a);

        var d= arrayListOf("1","2","3");
        a@ for (i in d)
        {
            println()
            for (j in d)
            {
                if (j.equals("2"))
                {
                    break@a
                }
            }
            println()
        }

        BClass(object:MyInterface{
            override fun say() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override var v: String
                get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
                set(value) {}

        });
       var bb= BClass(object :MyInterface{
            override var v: String = ""
                get() = field;
                set(value) {
                    field="1"+value;
                }

            override fun say() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        bb.prt();

        var c= CClass("")
        var bbb=CClass("1","2");

        fsda();

       var aa=Bean();
        aa.copy("");

        var x=1;
        var y=10;
        for (i in x..y)
        {
            println();
        }

        if (QTest()||QTest())
        {
            println()
        }

        EClass('1');
    }


    fun QTest():Boolean{
        return true;
    }


    /*
    * lamebda1
    * */
    fun lamebda1(){
        var sum:(Int,Int)->Int={x,y->x+y};
        var sum1={x:Int,y:Int->x+y};
        var sum2={x:Int,y:Int->{
            var s:Int=x+y;
            s;
        }}
        var a=sum(1,2);
        var b=sum1(1,3);
        var c=sum2(1,4);
        println("TEXT$c");
    }



    /*
    * 可变长
    * */
    fun testVararg(vararg s: String){
        for(a in s)
        {
            println("TEST"+a)
        }

        HelloY()
    }


    /*
    * 枚举
    * */
    enum class Color(val r: Int, val g: Int, val b: Int ){ //枚举常量属性

        // 定义枚举常量对象
        RED(255, 0, 0), ORANGE(255, 165, 0),
        YELLOW(255, 255, 0), GREEN(0, 255, 0),
        BLUE(0, 0, 255), INDIGO(75, 0, 130),
        VIOLET(238, 130, 238); //最后一个枚举对象需要分号结尾

        // 在枚举类中定义函数
        fun rgb():Int{
             return r * 256 + g* 256 + b;
        }


    }

    /*
    * 对象
    * */
    class Grass(var s:String){

        fun get():String{
            return s;
        }
    }

    fun test1(sum: () -> String?){
        var a:String?="aaa";
        println("TEST $a${'$'}  $sum")
    }

    fun Array(){
        var a= arrayListOf<String>();
        var b= listOf<String>();
        var c=Array<Int>(3,{x->x+1});
    }

    fun whenT(){
        var a=1;
        when(a){
            1->{}
            else->{};
        }

        var b=1;
        when{
            b in 1..2->{}
            b is Int->{}

        }
    }

    fun forT(){
        var a= arrayListOf("1",2);
        for (b in a.indices)
        {

        }

        for ((i,v) in a.withIndex()){

        }


    }

    class Test(var a:String);

    fun Test.hhh(){
        println(a)
    }

    class AClass(var has:String){
        var v:Boolean=true;
        inner class BClass{
            fun test(){
                println(has);
            }
        }
    }


    class BClass(var v:MyInterface){
        fun prt(){
            v.v="2";
            println("TETS${v.v}");
        }
    }

    public interface MyInterface{
        public var v:String;
        fun say();
    }

    open class CClass(){
        constructor(s: String) : this() {

        }

        constructor(s: String,ss:String) : this() {

        }

        open fun say(){

        }
    }

    class D:CClass{

        constructor() : super()
        constructor(s: String) : super(s)
        constructor(s: String, ss: String) : super(s, ss)

        override fun say() {
            super.say()
        }
    }

    class E:CClass(){
        override fun say() {
            super.say()
        }
    }

    open class Foo{
        val x:Int=1;

    }

    fun  String.test(a:Int):Char{
        return this.get(a);
    }

    class FClass(){
        var HH:Int=1;
        companion object {
            fun ss(){

            }
        }


    }

    fun FClass.foo(){
        this.HH;
    }


    fun FClass.Companion.foo(){
        println("")
    }

    fun fsda(){
        FClass.foo();
    }

    data class Bean(var name:String="",var hello:String="");

    fun EClass(char: Char){
        if (char=='1'){
            println();
        }


        var arr=Array(5){i->i*2};
        var arr2:Array<Int> = arrayOf(1,2);
        arr.forEach {
            print(it);
        }

    }
}
