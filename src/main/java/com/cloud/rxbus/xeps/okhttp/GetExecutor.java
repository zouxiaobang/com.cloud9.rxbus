package com.cloud.rxbus.xeps.okhttp;

import com.cloud.rxbus.http.component.IExecutor;
import com.cloud.rxbus.http.component.IRequest;
import com.cloud.rxbus.http.component.IResponse;
import com.cloud.rxbus.http.def.DefaultResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;

/**
 * @author xb.zou
 * @date 2017/10/26
 * @option GET请求方法处理器
 */
public class GetExecutor implements IExecutor {
    private OkHttpClient mClient;

    public GetExecutor(OkHttpClient client) {
        this.mClient = client;
    }

    public IResponse execute(IRequest request) {
        //设置请求方法
        request.setMethod(new GetMethod());
        //解析IRequest
        Request.Builder builder = new Request.Builder();
        Map<String, String> headers = request.getHeader();
        for (String key: headers.keySet()){
            builder.header(key, headers.get(key));
        }
        builder.url(request.getUrl()).get();
        //组装完成
        Request okRequest = builder.build();

        //开始执行请求，并返回IResponse对象
        IResponse response = new DefaultResponse();

        try {
            Response okResponse = mClient.newCall(okRequest).execute();
            response.setCode(okResponse.code());
            response.setData(okResponse.body().string());
        } catch (IOException e) {
            response.setCode(1001);
            response.setError(e.getMessage());
        }

        return response;
    }


}
