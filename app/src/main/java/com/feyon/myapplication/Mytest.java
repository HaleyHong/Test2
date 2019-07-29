package com.feyon.myapplication;

/**
 * Created by DS on 2018/7/30.
 */

public class Mytest {

    public class text1{
        public void hello(int a){

        }
    }

    public class text2 extends text1{

        @Override
        public void hello(int a) {
            super.hello(a);
        }

        public void hello(float a)
        {

        }
    }

    interface test3{
        public abstract void hello();
    }

    private class test4 implements test3{

        @Override
        public void hello() {

        }
    }


    private class test5 extends test4{
        @Override
        public void hello() {
            super.hello();
        }
    }

}
