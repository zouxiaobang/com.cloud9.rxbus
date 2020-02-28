package com.cloud.rxbus.http.component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author xb.zou
 * @date 2017/10/26
 * @option HttpClient的抽象管理类
 */
public class HttpClient implements IHttpClient {
    private ConcurrentMap<String, IExecutor> mExecutorMap = new ConcurrentHashMap<String, IExecutor>();

    private HttpClient() {}
    public static final HttpClient getInstance() {
        return Inner.INSTANCE;
    }
    public static class Inner {
        private static final HttpClient INSTANCE = new HttpClient();
    }

    /**
     * 将方法处理器注册进来
     * 注意：该方法会导致之前注册的处理器被丢失
     */
    public void registerExecutor(ConcurrentMap<IMethod, IExecutor> map) {
        this.mExecutorMap.clear();
        for (Map.Entry<IMethod, IExecutor> entry : map.entrySet()) {
            mExecutorMap.put(entry.getKey().getTypeText(), entry.getValue());
        }
    }

    /**
     * 将方法处理器注册进来
     */
    public void registerExecutor(IMethod method, IExecutor executor) {
        mExecutorMap.put(method.getTypeText(), executor);
    }

    /**
     * 注销方法处理器
     */
    public void unregisterExecutor(IMethod method) {
        String typeText = method.getTypeText();
        if (mExecutorMap.containsKey(typeText)) {
            mExecutorMap.remove(typeText);
        }
    }

    public IResponse execute(IMethod method, IRequest request) {
        for (Map.Entry<String, IExecutor> entry : mExecutorMap.entrySet()) {
            if (entry.getKey().equals(method.getTypeText())) {
                return entry.getValue().execute(request);
            }
        }

        throw new RuntimeException("没有该请求方法对应的处理器");
    }
}
