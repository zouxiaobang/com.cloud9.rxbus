package com.cloud.rxbus.bus;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * @author xb.zou
 * @date 2017/10/26
 * @option 事件驱动处理类
 */
public class Bus {
    /**
     * 订阅者集合
     */
    private Set<Object> mSubscriberSet = new HashSet<Object>();

    private Bus() {}
    public static Bus getInstance() {
        return Inner.INSTANCE;
    }
    public static class Inner {
        private static final Bus INSTANCE = new Bus();
    }

    public void registerSubscriber(Object subscriber) {
        mSubscriberSet.add(subscriber);
    }

    public void unregisterSubscriber(Object subscriber) {
        mSubscriberSet.remove(subscriber);
    }

    public void executeProcess(Function f) {
        Observable.just("")
                .map(f)
                .subscribe(new Observer() {
                    public void onSubscribe(Disposable disposable) {

                    }

                    public void onNext(Object data) {
                        if (data == null) {
                            return;
                        }
                        send(data);
                    }

                    public void onError(Throwable throwable) {

                    }

                    public void onComplete() {

                    }
                });
    }

    private void send(Object data) {
        for (Object subscriber : mSubscriberSet) {
            callByAnntiation(subscriber, data);
        }
    }

    private void callByAnntiation(Object target, Object data) {
        //获取target中所有的方法
        Method[] methods = target.getClass().getDeclaredMethods();

        for (Method method : methods) {
            try {
                //判断该方法是否是被RegisterBus注册监听的
                boolean isAnntationMethod = method.isAnnotationPresent(RegisterBus.class);

                if (isAnntationMethod) {
                    //获取该方法第一个参数的类型
                    Class paramType = method.getParameterTypes()[0];
                    //判断该参数的类型是否和data的参数类型是一致的
                    if (paramType.equals(data.getClass())) {
                        //执行该方法
                        method.invoke(target, new Object[]{data});
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

    }
}
