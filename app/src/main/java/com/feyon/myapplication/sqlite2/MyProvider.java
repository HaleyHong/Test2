package com.feyon.myapplication.sqlite2;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class MyProvider extends ContentProvider {

    private Context mContext;
    private DBHelper mDbHelper=null;
    private SQLiteDatabase db=null;

    public static final String  AUTOHOTITY="cn.scu.myprovider";

    public static final  int User_Code=1;
    public static final int Job_Code=2;

    private static UriMatcher mMatcher;


    static {
        mMatcher=new UriMatcher(UriMatcher.NO_MATCH);
        mMatcher.addURI(AUTOHOTITY,"user",User_Code);
        mMatcher.addURI(AUTOHOTITY,"job",Job_Code);

        // 若URI资源路径 = content://cn.scu.myprovider/user ，则返回注册码User_Code
        // 若URI资源路径 = content://cn.scu.myprovider/job ，则返回注册码Job_Code
    }

    @Override
    public boolean onCreate() {

        mContext=getContext();
        mDbHelper=new DBHelper(mContext);
        db= mDbHelper.getWritableDatabase();

        // 初始化两个表的数据(先清空两个表,再各加入一个记录)
        db.execSQL("delete from user");
        db.execSQL("insert into user values(1,'Carson');");
        db.execSQL("insert into user values(2,'Kobe');");

        db.execSQL("delete from job");
        db.execSQL("insert into job values(1,'Android');");
        db.execSQL("insert into job values(2,'iOS');");

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {

        String tableName=getTableName(uri);

        return db.query(tableName,strings,s,strings1,null,null,s1,null);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {

        String tableName=getTableName(uri);
        db.insert(tableName,null,contentValues);
        mContext.getContentResolver().notifyChange(uri,null);
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    private String getTableName(Uri uri)
    {
        String tableName=null;
        switch (mMatcher.match(uri))
        {
            case User_Code:
                tableName=DBHelper.USER_TABLE_NAME;
                break;
            case Job_Code:
                tableName=DBHelper.JOB_TABLE_NAME;
                break;
        }
        return tableName;
    }
}
