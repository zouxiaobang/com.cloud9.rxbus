package com.cloud.rxbus.http.component;

/**
 * @author xb.zou
 * @date 2017/10/26
 * @option 方法类型的抽象类
 */
public interface IMethod {
    /**
     * 返回该方法的文字描述
     * 例如：return "GET"
     */
    String getTypeText();
}
