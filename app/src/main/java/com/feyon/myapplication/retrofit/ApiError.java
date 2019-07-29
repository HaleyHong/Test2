package com.feyon.myapplication.retrofit;

import com.feyon.myapplication.Constants;

/**
 * Created by DS on 2018/6/29.
 */

public class ApiError extends RuntimeException {
    private int mErrorCode;

    public ApiError(int errorCode, String errorMessage) {
        super(errorMessage);
        mErrorCode = errorCode;
    }

    /**
     * 判断是否是token失效
     *
     * @return 失效返回true, 否则返回false;
     */
    public boolean isTokenExpried() {
        return mErrorCode == Constants.TOKEN_EXPRIED;

    }

}
