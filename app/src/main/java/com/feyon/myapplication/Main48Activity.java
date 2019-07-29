package com.feyon.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.feyon.myapplication.glide.AlbumGlideEngine;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

public class Main48Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main48);

        View view=findViewById(R.id.hello1);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Matisse.from(Main48Activity.this)
                        .choose(MimeType.ofImage())//图片类型
                        .countable(true)//true:选中后显示数字;false:选中后显示对号
                        .maxSelectable(6)//可选的最大数
                        .capture(false)//选择照片时，是否显示拍照
                        .captureStrategy(new CaptureStrategy(true, ""))//参数1 true表示拍照存储在共有目录，false表示存储在私有目录；参数2与 AndroidManifest中authorities值相同，用于适配7.0系统 必须设置
                        .imageEngine(new AlbumGlideEngine())//图片加载引擎
                        .theme(R.style.Matisse_Dracula)
                        .forResult(0);
            }
        });
    }
}
