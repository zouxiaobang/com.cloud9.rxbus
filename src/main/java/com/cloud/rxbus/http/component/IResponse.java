package com.cloud.rxbus.http.component;

/**
 * @author xb.zou
 * @date 2017/10/26
 * @option 返回类接口
 */
public interface IResponse {
    /**
     * 设置返回码
     */
    void setCode(int code);

    /**
     * 获取返回码
     */
    int getCode();

    /**
     * 设置返回内容
     */
    void setData(String data);

    /**
     * 获取返回内容
     */
    String getData();

    void setError(String errMsg);
    String getError();
}
