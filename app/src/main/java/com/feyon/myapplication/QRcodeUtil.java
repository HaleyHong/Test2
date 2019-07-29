package com.feyon.myapplication;

import android.graphics.Bitmap;
import android.os.Environment;

import com.feyon.FileUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by DS on 2018/10/10.
 */

public class QRcodeUtil {

    private static String path= Environment.getExternalStorageDirectory()+File.separator+"test";
    private static final int mwidth=400;
    private static final int mheght=200;

    private static Bitmap generateBitmap(String content, int width, int height) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.MARGIN, 1);
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        try {
            BitMatrix encode = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            int[] pixels = new int[width * height];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (encode.get(j, i)) {
                        pixels[i * width + j] = 0x00000000;
                    } else {
                        pixels[i * width + j] = 0xffffffff;
                    }
                }
            }
            return Bitmap.createBitmap(pixels, 0, width, width, height, Bitmap.Config.ARGB_8888);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static File generateFile(String content){
        return generateFile(content,mwidth,mheght);
    }

    public static File generateFile(String content, int width, int height){

        String fileName=System.currentTimeMillis()+"";
        Bitmap bitmap=generateBitmap(content,width,height);

        File file= FileUtil.createFile(path,fileName);

        try {
            FileOutputStream fileOutputStream=new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);
            bitmap.recycle();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        return file;
    }

    public static void clearAllQRCode(){
        FileUtil.deleteDir(new File(path));
    }
}
