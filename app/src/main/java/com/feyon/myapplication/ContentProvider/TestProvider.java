package com.feyon.myapplication.ContentProvider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import org.litepal.LitePal;

/**
 * Created by DS on 2018/8/13.
 */

public class TestProvider extends ContentProvider {

    public static final String AUTOHORITY = "me.pengtao.contentprovidertest";
    // 设置ContentProvider的唯一标识

    public static final int User_Code = 1;
    public static final int Job_Code = 2;

    private static final UriMatcher mMatcher;
    static{
        mMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        // 初始化
        mMatcher.addURI(AUTOHORITY,"demo", User_Code);
        mMatcher.addURI(AUTOHORITY, "demo", Job_Code);
        // 若URI资源路径 = content://cn.scu.myprovider/user ，则返回注册码User_Code
        // 若URI资源路径 = content://cn.scu.myprovider/job ，则返回注册码Job_Code
    }



    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        Log.i("HHHHHHHH", "query: w");
        SQLiteDatabase sqLiteDatabase= LitePal.getDatabase();
////
        Cursor cursor=sqLiteDatabase.query(getTableName(uri),strings,s,strings1,s1,null,null);
        Log.i("TAG", "query: 1");
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    /**
     * 根据URI匹配 URI_CODE，从而匹配ContentProvider中相应的表名
     */
    private String getTableName(Uri uri){
        String tableName = null;
        switch (mMatcher.match(uri)) {
            case User_Code:
                tableName = "mod1";
                break;
            case Job_Code:
                tableName = "mod1";
                break;
        }
        return tableName;
    }

}
