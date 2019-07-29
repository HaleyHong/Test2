package com.feyon.myapplication;

import android.app.Application;
import android.content.Context;

import org.litepal.LitePal;

import cn.jpush.android.api.JPushInterface;

//import com.tencent.bugly.Bugly;
//import com.tencent.bugly.beta.Beta;
//import com.tencent.bugly.beta.interfaces.BetaPatchListener;
//import com.tencent.bugly.beta.upgrade.UpgradeStateListener;


/**
 * For developer startup JPush SDK
 * 
 * 一般建议在自定义 Application 类里初始化。也可以在主 Activity 里。
 */


public class ExampleApplication extends Application {
    private static final String TAG = "JIGUANG-Example";
    public static ExampleApplication exampleApplication;

    @Override
    public void onCreate() {
         super.onCreate();

        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush
        exampleApplication=this;

        LitePal.initialize(this);

//        Beta.betaPatchListener = new BetaPatchListener() {
//            @Override
//            public void onPatchReceived(String patchFile) {
//                Toast.makeText(getMYContext(), "补丁下载地址" + patchFile, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onDownloadReceived(long savedLength, long totalLength) {
//                Toast.makeText(getMYContext(),
//                        String.format(Locale.getDefault(), "%s %d%%",
//                                Beta.strNotificationDownloading,
//                                (int) (totalLength == 0 ? 0 : savedLength * 100 / totalLength)),
//                        Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onDownloadSuccess(String msg) {
//                Toast.makeText(getMYContext(), "补丁下载成功", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onDownloadFailure(String msg) {
//                Toast.makeText(getMYContext(), "补丁下载失败", Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onApplySuccess(String msg) {
//                Toast.makeText(getMYContext(), "补丁应用成功", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onApplyFailure(String msg) {
//                Toast.makeText(getMYContext(), "补丁应用失败", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onPatchRollback() {
//                Toast.makeText(getMYContext(), "回滚", Toast.LENGTH_SHORT).show();
//            }
//        };
//        Beta.upgradeStateListener=new UpgradeStateListener() {
//            @Override
//            public void onUpgradeFailed(boolean b) {
//                Toast.makeText(getMYContext(), "onUpgradeFailed", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onUpgradeSuccess(boolean b) {
//                Toast.makeText(getMYContext(), "onUpgradeSuccess", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onUpgradeNoVersion(boolean b) {
//                Toast.makeText(getMYContext(), "onUpgradeNoVersion", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onUpgrading(boolean b) {
//                Toast.makeText(getMYContext(), "onUpgrading", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onDownloadCompleted(boolean b) {
//                Toast.makeText(getMYContext(), "onDownloadCompleted", Toast.LENGTH_SHORT).show();
//            }
//        };
//        Bugly.init(this, "69b7d6ecd9", true);

    }

    @Override
    protected void attachBaseContext(android.content.Context base) {
        super.attachBaseContext(base);
        android.support.multidex.MultiDex.install(this);

//        Beta.installTinker();
    }

    public static Context getMYContext(){
        return exampleApplication.getApplicationContext();
    }
}
