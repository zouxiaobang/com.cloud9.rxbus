package com.cloud.rxbus.http.component;

import java.util.Map;

/**
 * @author xb.zou
 * @date 2017/10/26
 * @option 请求类接口
 */
public interface IRequest {

    /**
     * 设置请求头
     */
    void setHeader(String key, String value);

    /**
     * 设置请求参数
     */
    void setBody(String key, Object value);

    /**
     * 设置请求方法
     */
    void setMethod(IMethod method);

    /**
     * 获取请求头
     */
    Map<String, String> getHeader();

    /**
     * 获取URL
     */
    String getUrl();

    /**
     * 获取请求参数
     */
    Object getBody();

    /**
     * 是否对请求方法进行推测
     */
    void setGuess(boolean guess);
}
