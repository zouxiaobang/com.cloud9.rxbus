package com.cloud.rxbus.http.component;

/**
 * @author xb.zou
 * @date 2017/10/26
 * @option Http请求通用接口
 */
public interface IHttpClient {
    /**
     * 请求网络的执行方法
     */
    IResponse execute(IMethod method, IRequest request);
}
