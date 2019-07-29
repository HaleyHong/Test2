package com.feyon.myapplication.ok_re_rx;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.feyon.myapplication.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class Main15Activity extends AppCompatActivity {
    private ImageView imageView;
    private ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
    private OutputStream fileOutputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main15);

        try {
            fileOutputStream=new FileOutputStream(getFile());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        imageView=findViewById(R.id.myview);

        init();
    }

    private void init(){
        GetImageUrl getImageUrl= NewWorkInstance.getRetrofit().newBuilder().baseUrl("http://test.10000s.com/").build().create(GetImageUrl.class);

        String range= "bytes=" + byteArrayOutputStream.size() + "-";
        getImageUrl.getImage2(range).observeOn(Schedulers.io()).subscribeOn(Schedulers.io()).subscribe(new Observer<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {

                byte[] b=new byte[1024];
                InputStream inputStream= responseBody.byteStream();
                int len=0;

                try {
                    while ((len=inputStream.read(b))!=-1)
                    {
                        byteArrayOutputStream.write(b,0,len);
                        byteArrayOutputStream.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();

                }

                OutputStream outputStream;
                final byte[] b1=byteArrayOutputStream.toByteArray();


                try {
                    byteArrayOutputStream.close();
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

//                imageView.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        imageView.setImageBitmap(BitmapFactory.decodeByteArray(b1,0,b1.length));
//                    }
//                });

             /*   byte[] bytes=new byte[1024];
                int len=0;
                InputStream inputStream= responseBody.byteStream();
                try {
                    while ((len=inputStream.read(bytes))!=-1)
                    {
                        fileOutputStream.write(bytes,0,len);
                        fileOutputStream.flush();
                    }
                    inputStream.close();
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/


            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public File getFile(){
        File file=new File(Environment.getExternalStorageDirectory(),"MyApp");
        if (!file.exists())
        {
            file.mkdirs();
        }

       return new File(file.getPath()+"/app.jpg");

    }
}
