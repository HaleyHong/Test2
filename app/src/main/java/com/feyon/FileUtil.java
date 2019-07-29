package com.feyon;

import java.io.File;
import java.io.IOException;

/**
 * Created by DS on 2018/10/10.
 */

public class FileUtil {

    public static File createFile(String path,String name)
    {
        File file=new File(path+File.separator+name);
//        if (file.exists())
//        {
//            boolean is= file.mkdirs();
//        }
        File file1=file.getParentFile();

        if (file1.isDirectory())
        {
            System.out.println();
        }
        if (!file1.exists()) {
            boolean is= file1.mkdirs();
            System.out.println();
        }

        if (!file.exists())
        {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return file;
    }

    public static boolean deleteDir(File dir){
        if (dir == null) return false;
        // dir doesn't exist then return true
        if (!dir.exists()) return true;
        // dir isn't a directory then return false
        if (!dir.isDirectory()) return false;
        File[] files = dir.listFiles();
        if (files != null && files.length != 0) {
            for (File file : files) {
                if (file.isFile()) {
                    if (!file.delete()) return false;
                } else if (file.isDirectory()) {
                    if (!deleteDir(file)) return false;
                }
            }
        }

        return true;
    }


}
