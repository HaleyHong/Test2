package com.feyon.myapplication;


import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.LinearLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main7Activity extends AppCompatActivity {
     private MediaRecorder mediaRecorder;
     private CameraPreView cameraPreView;
     private Camera camera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        camera=getCameraInstance();
        Camera.Parameters parameters=camera.getParameters();
        Camera.Size previews=null;

        if (parameters!=null)
        {
            try{
                previews=findFitPreResolution(parameters);
            }catch (Exception e)
            {
                e.printStackTrace();
            }
            parameters.setPreviewSize(previews.width,previews.height);
            parameters.setJpegQuality(80);
            parameters.setPictureSize(previews.width,previews.height);
            camera.setParameters(parameters);
        }


        //
        cameraPreView=new CameraPreView(this,camera);
        LinearLayout linearLayout= findViewById(R.id.my_l);
        linearLayout.addView(cameraPreView);


        Main7Activity.this.findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                star();

            }
        });

        Main7Activity.this.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                if (mediaRecorder!=null)
                {
                    mediaRecorder.stop();
                    mediaRecorder.reset();
                    mediaRecorder.release();
                    mediaRecorder=null;
                }

                if (camera!=null)
                {
                    camera.lock();
                }

            }
        });

        Main7Activity.this.findViewById(R.id.start2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if (mediaRecorder!=null)
                    {
                        mediaRecorder.stop();
                        mediaRecorder.release();
                        mediaRecorder=null;
                    }

                    camera.takePicture(null,null,pictureCallback);
                }catch (Exception e)
                {
                    System.out.println("");
                }

            }
        });

    }

    private void star(){
        camera.unlock();
        mediaRecorder= new MediaRecorder();
        mediaRecorder.setCamera(camera);
        mediaRecorder.reset();
        mediaRecorder.setPreviewDisplay(cameraPreView.getHolder().getSurface());
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);

        // Step 3: Set a CamcorderProfile (requires API Level 8 or higher)
        mediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH));
        File file=getOutputMediaFile(MEDIA_TYPE_VIDEO,Main7Activity.this);

        mediaRecorder.setOutputFile(file.getPath());

        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaRecorder.start();

    }

    @Override
    protected void onResume() {
        super.onResume();
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
                this.camera.setPreviewDisplay(holder);
                this.camera.startPreview();




            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            if (surfaceHolder.getSurface()==null)
                return;

            try {
                camera.stopPreview();
            }catch (Exception e){

            }

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


    public static Camera getCameraInstance(){
        Camera c=null;
        try {
            c=Camera.open();
        }catch (Exception e)
        {

        }

        return c;
    }

    public boolean checkCameraHardware(Context context){
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            return true;
        }else {
            return false;
        }
    }

    private Camera.Size findFitPreResolution(Camera.Parameters parameters)
    {
        List<Camera.Size> supportedPicResolutions=parameters.getSupportedPreviewSizes();
        Camera.Size resultSize=null;
        for (Camera.Size size:supportedPicResolutions)
        {
            if (size.width<=2000)
            {
                if (resultSize==null)
                {
                    resultSize=size;
                }else if (size.width>resultSize.width)
                {
                    resultSize=size;
                }
            }
        }

        if (resultSize==null)
        {
            return supportedPicResolutions.get(0);
        }

        return resultSize;
    }

    public static final int MEDIA_TYPE_IMAGE=1;
    public static final int MEDIA_TYPE_VIDEO=2;

    private static Uri getOutputMediaFileUri(int type, Context context){
        return Uri.fromFile(getOutputMediaFile(type,context));
    }

    private static File getOutputMediaFile(int type, Context context){



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


    private Camera.PictureCallback pictureCallback=new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            File file=getOutputMediaFile(MEDIA_TYPE_IMAGE,Main7Activity.this);
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
}
