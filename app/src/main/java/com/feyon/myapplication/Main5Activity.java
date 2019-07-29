package com.feyon.myapplication;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class Main5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        ViewGroup viewGroup=findViewById(R.id.my_frame);

        final Camera camera=getCameraInstance();
        Camera.Parameters parameters = camera.getParameters();//获取各项参数
        Camera.Size previewSize = null;
        try {
            previewSize = findFitPreResolution(parameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
        parameters.setPreviewSize(previewSize.width, previewSize.height);// 设置预览大小

//        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
//        parameters.setPictureFormat(PixelFormat.JPEG);//设置图片格式
        //不能与setPreviewSize一起使用，否则setParamters会报错
//                    parameters.setPreviewFrameRate(5);//设置每秒显示4帧
        parameters.setJpegQuality(80);// 设置照片质量
//        Camera.Size pictureSize = null;
//        if (equalRate(screenWidth, screenHeight, 1.33f)) {
//            pictureSize = findFitPicResolution(parameters, (float) 4 / 3);
//        } else {
//            pictureSize = findFitPicResolution(parameters, (float) 16 / 9);
//        }

        parameters.setPictureSize(previewSize.width, previewSize.height);// 设置保存的图片尺寸
        camera.setParameters(parameters);

        CameraPreView cameraPreView=new CameraPreView(this,camera);
        viewGroup.addView(cameraPreView);
//        Camera.Parameters params = camera.getParameters();
//        params.setPictureFormat(PixelFormat.JPEG);//图片格式
//        params.setPreviewSize(800, 480);//图片大小
//        camera.setParameters(params);//将参数设置到我的camera
        findViewById(R.id.my_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                camera.autoFocus(new Camera.AutoFocusCallback() {
                    @Override
                    public void onAutoFocus(boolean success, Camera camera) {
                        if (success)
                        {
                            camera.takePicture(null,null,pictureCallback);
                        }
                    }
                });
//                camera.takePicture(null,null,pictureCallback);
            }
        });
    }

    private void initMedia(){

    }

    private boolean checkCameraHardware(Context context)
    {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA))
        {
            return true;
        }else {
            return false;
        }
    }

    public static Camera getCameraInstance(){
        Camera c=null;
        try {
            c= Camera.open();
        }catch(Exception e){

        }
        return c;
    }

    public class CameraPreView extends SurfaceView implements SurfaceHolder.Callback{

        private SurfaceHolder surfaceHolder;
        private Camera camera;

        public CameraPreView(Context context,Camera camera) {
            super(context);

            this.camera=camera;

            surfaceHolder=getHolder();
            surfaceHolder.addCallback(this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            try {
                camera.setPreviewDisplay(holder);
                camera.startPreview();
            }catch (IOException e)
            {

            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            if (surfaceHolder.getSurface()==null)
                return;

            try {
                camera.stopPreview();
            }catch (Exception e){

            };

            try {
                camera.setPreviewDisplay(holder);
                camera.startPreview();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    }

    private Camera.PictureCallback pictureCallback=new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            File file=getOutputMediaFile(MEDIA_TYPE_IMAGE,Main5Activity.this);
            if (file==null)
            {
                return;
            }

            try {
                FileOutputStream fos=new FileOutputStream(file);
                fos.write(data);
                fos.close();

                camera.stopPreview();//关闭预览 处理数据
                camera.startPreview();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };





    public static final int MEDIA_TYPE_IMAGE=1;
    public static final int MEDIA_TYPE_VIDEO=2;

    private static Uri getOutputMediaFileUri(int type,Context context){
        return Uri.fromFile(getOutputMediaFile(type,context));
    }

    private static File getOutputMediaFile(int type,Context context){



        File mdieaStorage=new File(Environment.getExternalStorageDirectory(),"MyCameraApp");

        long lenght=mdieaStorage.length();
        if (!mdieaStorage.exists())
        {
            if (!mdieaStorage.mkdirs())
            {
                return null;
            }
        }

        String timeStamp=new SimpleDateFormat("yyyyyMMdd_HHmmss").format(new Date());

        File mediaFile;

        if (type==MEDIA_TYPE_IMAGE)
        {
            mediaFile=new File(mdieaStorage.getPath()+File.separator+"IMG_"+timeStamp+".jpg");
        }else if (type==MEDIA_TYPE_VIDEO)
        {
            mediaFile=new File(mdieaStorage.getPath()+File.separator+"VID_"+timeStamp+".mp4");
        }else {
            return null;
        }

        return mediaFile;
    }

    /**
     * 返回合适的预览尺寸参数
     *
     * @param cameraParameters
     * @return
     */
    private Camera.Size findFitPreResolution(Camera.Parameters cameraParameters) throws Exception {
        List<Camera.Size> supportedPicResolutions = cameraParameters.getSupportedPreviewSizes();

        Camera.Size resultSize = null;
        for (Camera.Size size : supportedPicResolutions) {
            if (size.width <= 2000) {
                if (resultSize == null) {
                    resultSize = size;
                } else if (size.width > resultSize.width) {
                    resultSize = size;
                }
            }
        }
        if (resultSize == null) {
            return supportedPicResolutions.get(0);
        }
        return resultSize;
    }

    private boolean equalRate(int width, int height, float rate) {
        float r = (float) width / (float) height;
        if (Math.abs(r - rate) <= 0.2) {
            return true;
        } else {
            return false;
        }
    }

}
