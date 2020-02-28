package com.cloud.rxbus.xeps.okhttp;

import com.cloud.rxbus.http.component.IMethod;

/**
 * @author xb.zou
 * @date 2017/10/26
 * @option OkHttp下的POST请求方法
 */
public class PostMethod implements IMethod {
    public String getTypeText() {
        return "POST";
    }
}
