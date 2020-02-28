package com.cloud.rxbus.bus;

import java.lang.annotation.*;

/**
 * @author xb.zou
 * @date 2017/10/26
 * @option 注册该事件的注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RegisterBus {

}
