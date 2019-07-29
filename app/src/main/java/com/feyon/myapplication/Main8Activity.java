package com.feyon.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.media.ImageReader;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Arrays;

public class Main8Activity extends AppCompatActivity {

    private CameraDevice cameraDevice;
    private SurfaceHolder surfaceHolder;
    private ImageReader imageReader;

    private Handler childHandler;

    private CameraCaptureSession cameraCaptureSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);


    }

    @Override
    protected void onResume() {
        super.onResume();
        InitView();
    }

    private CameraDevice.StateCallback stateCallback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(@NonNull CameraDevice camera) {
            cameraDevice = camera;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                takePreView();
            }
        }

        @Override
        public void onDisconnected(@NonNull CameraDevice camera) {
            if (null != cameraDevice) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    cameraDevice.close();
                }
                ;
                cameraDevice = null;
            }
        }

        @Override
        public void onError(@NonNull CameraDevice camera, int error) {
            Log.i("TAG", "onError: 打开相机失败");
        }
    };

    public void InitView() {

        SurfaceView surfaceView =findViewById(R.id.my_surface);

//        surfaceView.setKeepScreenOn(true);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    initCamera();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initCamera() {
        HandlerThread handlerThread = new HandlerThread("");
        handlerThread.start();

        childHandler = new Handler(handlerThread.getLooper());
        Handler mainHandler = new Handler(getMainLooper());

        imageReader = ImageReader.newInstance(3264, 1000, ImageFormat.JPEG, 2);
        imageReader.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() {
            @Override
            public void onImageAvailable(ImageReader reader) {

            }
        }, null);


        CameraManager cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            cameraManager.openCamera(CameraCharacteristics.LENS_FACING_FRONT + "", stateCallback, mainHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void takePreView(){

        try {
            final CaptureRequest.Builder preCaptureRequest=cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);

        if (preCaptureRequest==null)
            return;
        preCaptureRequest.addTarget(surfaceHolder.getSurface());
        try {

            cameraDevice.createCaptureSession(Arrays.asList(surfaceHolder.getSurface(), imageReader.getSurface()), new CameraCaptureSession.StateCallback() {
                @Override
                public void onConfigured(@NonNull CameraCaptureSession session) {
                    cameraCaptureSession=session;
                    preCaptureRequest.set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);
                    // 打开闪光灯
                    preCaptureRequest.set(CaptureRequest.CONTROL_AE_MODE, CaptureRequest.FLASH_MODE_OFF);
//                    finalPreCaptureRequest.set(CaptureRequest.CONTROL_AF_MODE,CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);
                    CaptureRequest captureRequest=preCaptureRequest.build();
                    try {
                        cameraCaptureSession.setRepeatingRequest(captureRequest, null, childHandler);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onConfigureFailed(@NonNull CameraCaptureSession session) {
                    System.out.println("");
                }
            }, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
}
