package com.feyon.myapplication.sqlite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.feyon.myapplication.R;

import org.litepal.LitePal;

public class Main21Activity extends AppCompatActivity {

//    private static final Uri CONTENT_URI = Uri.parse("content://me.pengtao.contentprovidertest/test");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main21);

        init();
    }

    private void init(){



      mod1 b=new mod1();
      b.setAge(20);
      b.setName("hello");
      b.save();

      mod1 b2=new mod1();
      b2.setAge(20);
      b2.setName("hello2");
      b2.save();

      mod1 b3=new mod1();
      b3.setAge(30);
      b3.setName("hello3");
      b3.save();

        //根据id 更新
        mod1 a= LitePal.find(mod1.class,1);
      if (b.save())
      {
          System.out.println("存储成功"+a.getName());
      }


      //替换
        mod1 c=new mod1();
        c.setAge(21);
        c.setName("ccccc");
        c.update(1);

        //条件替换

        mod1 d=new mod1();
        d.setName("DDDDDDD");
        d.updateAll("name=?","ccccc");

        LitePal.find(mod1.class,1);




    }
}
