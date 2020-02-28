package com.cloud.rxbus.http.component;

/**
 * @author xb.zou
 * @date 2017/10/26
 * @option 方法对应执行类接口
 */
public interface IExecutor {
    /**
     * 请求网络的执行方法
     */
    IResponse execute(IRequest request);
}
