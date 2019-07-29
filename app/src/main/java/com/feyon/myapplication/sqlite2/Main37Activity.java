package com.feyon.myapplication.sqlite2;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.feyon.myapplication.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main37Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println(this);
        setContentView(R.layout.activity_main37);

        Uri uri=Uri.parse("content://cn.scu.myprovider/user");

        ContentValues values=new ContentValues();
        values.put("_id",3);
        values.put("name","IVerson");

        ContentResolver contentResolver=getContentResolver();
        contentResolver.insert(uri,values);

        Cursor cursor=contentResolver.query(uri,new String[]{"_id","name"},null,null,null);
        while (cursor.moveToNext()){
            System.out.println("query book:" + cursor.getInt(0) +" "+ cursor.getString(1));
            // 将表中数据全部输出
        }
        cursor.close();

        /**
         * 对job表进行操作
         */
        // 和上述类似,只是URI需要更改,从而匹配不同的URI CODE,从而找到不同的数据资源
        Uri uri_job = Uri.parse("content://cn.scu.myprovider/job");

        // 插入表中数据
        ContentValues values2 = new ContentValues();
        values2.put("_id", 3);
        values2.put("job", "NBA Player");

        // 获取ContentResolver
        ContentResolver resolver2 =  getContentResolver();
        // 通过ContentResolver 根据URI 向ContentProvider中插入数据
        resolver2.insert(uri_job,values2);

        // 通过ContentResolver 向ContentProvider中查询数据
        Cursor cursor2 = resolver2.query(uri_job, new String[]{"_id","job"}, null, null, null);
        while (cursor2.moveToNext()){
            System.out.println("query job:" + cursor2.getInt(0) +" "+ cursor2.getString(1));
            // 将表中数据全部输出
        }
        cursor2.close();

        Pattern p = Pattern.compile("[0-9]{4}(\\.)[0-9]{2}");  //编译"a*b"为Pattern类的实例
        Matcher m = p.matcher("2017.09.01-2018.07.01");
        int i=0;
        while (m.find())
        {
            String s =m.group(0);
            System.out.println();
        }



    }
}
