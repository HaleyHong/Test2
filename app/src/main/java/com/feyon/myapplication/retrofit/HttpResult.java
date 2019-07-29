package com.feyon.myapplication.retrofit;

import com.feyon.myapplication.Constants;

/**
 * Created by DS on 2018/6/29.
 */

public class HttpResult <T>{
//    http://test.10000s.com//cfapp/app.spring?method=appGetTicketList&userCode=18146612901&pw=1104959D53DC3B60F2D40CD4A47D79E7&versionDate=2018-03-29&jpushRegId=A_010e926ad33&checkLoginCode=20180428142204
    private int stat;
    private int code;
    private T content;

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    private String mMessage;



    public String getMessage() {
        return mMessage;
    }

    /**
     * API是否请求失败
     *
     * @return 失败返回true, 成功返回false
     */
    public boolean isCodeInvalid() {
        return code == Constants.WEB_RESP_CODE_SUCCESS;
    }


}
