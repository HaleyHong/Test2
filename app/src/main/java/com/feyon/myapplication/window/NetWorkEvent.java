package com.feyon.myapplication.window;

/**
 * Created by DS on 2018/7/31.
 */

public class NetWorkEvent {

    boolean isConnect;

    public NetWorkEvent(boolean isConnect) {
        this.isConnect = isConnect;
    }

    public boolean isConnect() {
        return isConnect;
    }

    public void setConnect(boolean connect) {
        isConnect = connect;
    }
}
