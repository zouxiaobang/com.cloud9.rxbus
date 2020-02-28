package com.cloud.rxbus.http.def;

import com.cloud.rxbus.http.component.IResponse;

/**
 * @author xb.zou
 * @date 2017/10/26
 * @option 返回类的默认实现
 */
public class DefaultResponse implements IResponse {
    private int mCode;
    private String mData;
    private String mErrMsg;

    public void setCode(int code) {
        this.mCode = code;
    }

    public int getCode() {
        return mCode;
    }

    public void setData(String data) {
        this.mData = data;
    }

    public String getData() {
        return mData;
    }

    public void setError(String errMsg) {
        this.mErrMsg = errMsg;
    }

    public String getError() {
        return mErrMsg;
    }

    @Override
    public String toString() {
        return "DefaultResponse{" +
                "mCode=" + mCode +
                ", mData=" + mData +
                ", mErrMsg='" + mErrMsg + '\'' +
                '}';
    }
}
