package com.feyon.myapplication;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Size;
import android.view.Surface;
import android.view.TextureView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main9Activity extends AppCompatActivity {

    private Size mPreViewSize;
    private Size mCaptureSize;
    private String mCameraId;
    private CameraManager cameraManager;
    private CameraDevice mCameraDevice;
    private ImageReader imageReader;

    private TextureView textureView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);

        textureView=findViewById(R.id.my_test);
    }

    @Override
    protected void onResume() {
        super.onResume();

        textureView.setSurfaceTextureListener(surfaceTextureListener);
    }

    private TextureView.SurfaceTextureListener surfaceTextureListener = new TextureView.SurfaceTextureListener() {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
            //当SurefaceTexture可用的时候，设置相机参数并打开相机
            setupCamera(width, height);
            OpenCamera();
        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
            return false;
        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surface) {

        }
    };



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupCamera(int width, int height) {
        //获取摄像头的管理者CameraManager
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            //遍历所有摄像头
            for (String cameraId : cameraManager.getCameraIdList()) {
                CameraCharacteristics cameraCharacteristics = cameraManager.getCameraCharacteristics(cameraId);
                Integer facing = cameraCharacteristics.get(CameraCharacteristics.LENS_FACING);
                //此处默认打开后置摄像头
                if (facing != null && facing == CameraCharacteristics.LENS_FACING_FRONT)
                    continue;
                //获取StreamConfigurationMap,他是管理摄像头支持的所有格式和尺寸
                StreamConfigurationMap map = cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);

                assert map != null;
                //根据TextureView的尺寸设置预览尺寸
                mPreViewSize = getOptimalSize(map.getOutputSizes(SurfaceTexture.class), width, height);
                //获取相机的最大拍照尺寸
                mCaptureSize = Collections.max(Arrays.asList(map.getOutputSizes(ImageFormat.JPEG)), new Comparator<Size>() {
                    @Override
                    public int compare(Size o1, Size o2) {
                        return Long.signum(o1.getHeight() * o1.getWidth() - o2.getWidth() * o2.getHeight());
                    }
                });
                //此处用于拍照所需
                setupImageReader();
                mCameraId = cameraId;
                break;
            }
        } catch (Exception e) {

        }
    }

    //选择sizemap中大于并且最接近width和height的size
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private Size getOptimalSize(Size[] sizeMap, int width, int height) {
        List<Size> sizeList = new ArrayList<>();
        for (Size option : sizeMap) {
            if (width > height) {
                if (option.getWidth() > width && option.getHeight() > height) {
                    sizeList.add(option);
                }
            } else {
                if (option.getWidth() > height && option.getHeight() > width) {
                    sizeList.add(option);
                }
            }
        }

        if (sizeList.size() > 0) {
            return Collections.min(sizeList, new Comparator<Size>() {
                @Override
                public int compare(Size o1, Size o2) {
                    return Long.signum(o1.getHeight() * o1.getWidth() - o2.getHeight() * o2.getWidth());
                }
            });
        }
        return sizeMap[0];
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void setupImageReader() {
        //2代表ImageReader中最多可以获取两帧图像流
        imageReader = ImageReader.newInstance(mCaptureSize.getWidth(), mCaptureSize.getHeight(), ImageFormat.JPEG, 1);

        imageReader.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() {
            @Override
            public void onImageAvailable(ImageReader reader) {

                final Image image = reader.acquireNextImage();


            }
        }, null);
    }

    private void OpenCamera() {
        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
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
                cameraManager.openCamera(mCameraId, stateCallback, null);
            }
        }catch (Exception e)
        {

        }
    }

    private CameraDevice.StateCallback stateCallback=new CameraDevice.StateCallback() {
        @Override
        public void onOpened(@NonNull CameraDevice camera) {
            mCameraDevice=camera;
            startPreView();
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onDisconnected(@NonNull CameraDevice camera) {
            mCameraDevice.close();
            mCameraDevice=null;
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onError(@NonNull CameraDevice camera, int error) {
            mCameraDevice.close();
            mCameraDevice=null;
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void  startPreView(){
        SurfaceTexture surfaceTexture=textureView.getSurfaceTexture();

        surfaceTexture.setDefaultBufferSize(mPreViewSize.getWidth(),mPreViewSize.getHeight());

        Surface prSurface=new Surface(surfaceTexture);

        try {
            final CaptureRequest.Builder builder= mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);

            builder.addTarget(prSurface);

            mCameraDevice.createCaptureSession(Arrays.asList(prSurface, imageReader.getSurface()), new CameraCaptureSession.StateCallback() {
                @Override
                public void onConfigured(@NonNull CameraCaptureSession session) {
                   CaptureRequest captureRequest= builder.build();
                    try {
                        session.setRepeatingRequest(captureRequest,null,null);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onConfigureFailed(@NonNull CameraCaptureSession session) {

                }
            },null);
        }catch (Exception e)
        {

        }
    }
}
