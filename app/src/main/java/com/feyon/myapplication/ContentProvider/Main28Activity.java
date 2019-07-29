package com.feyon.myapplication.ContentProvider;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.feyon.myapplication.R;

import java.io.File;

public class Main28Activity extends AppCompatActivity {

    private static final Uri CONTENT_URI = Uri.parse("content://me.pengtao.contentprovidertest/demo");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main28);

        findViewById(R.id.my2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(Main28Activity.this,
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(Main28Activity.this,
                            new String[]{Manifest.permission.CAMERA},
                            1);

                } else {
//                    init();
                    ContentResolver resolver =  getContentResolver();
                    // 通过ContentResolver 根据URI 向ContentProvider中插入数据


                    // 通过ContentResolver 向ContentProvider中查询数据
                    Cursor cursor = resolver.query(CONTENT_URI, new String[]{"name"}, null, null, null);
                    while (cursor.moveToNext()){
                        System.out.println("query book:" +cursor.getString(0));
                        // 将表中数据全部输出
                    }
                    cursor.close();

                }

            }
        });
    }

    private void installApk() {
        // 需要自己修改安装包路径
        File file = new File(Environment.getExternalStorageDirectory(),
                "app-debug.apk");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri imageUri;
        if (Build.VERSION.SDK_INT>=24)
        {
            imageUri = FileProvider.getUriForFile(Main28Activity.this, "com.feyon.myapplication.fileProvider", file);//通过FileProvider创建一个content类型的Uri
            intent.addFlags(
                    Intent.FLAG_GRANT_READ_URI_PERMISSION |
                            Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        }else {
            imageUri = Uri.fromFile(file);//通过FileProvider创建一个content类型的Uri
        }

        intent.setDataAndType(imageUri,"application/vnd.android.package-archive");

        startActivity(intent);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void init(){
        File file=new File(Environment.getExternalStorageDirectory(), "/temp/"+System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists())file.getParentFile().mkdirs();
        Uri imageUri;
        if (Build.VERSION.SDK_INT>=24)
        {
            imageUri = FileProvider.getUriForFile(Main28Activity.this, "com.feyon.myapplication.fileProvider", file);//通过FileProvider创建一个content类型的Uri
        }else {
            imageUri = Uri.fromFile(file);//通过FileProvider创建一个content类型的Uri
        }

        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //添加这一句表示对目标应用临时授权该Uri所代表的文件
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);//设置Action为拍照
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);//将拍取的照片保存到指定URI
        startActivityForResult(intent,1006);
    }
}
