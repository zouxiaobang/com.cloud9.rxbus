package com.cloud.rxbus.xeps.okhttp;

import com.cloud.rxbus.http.component.IExecutor;
import com.cloud.rxbus.http.component.IRequest;
import com.cloud.rxbus.http.component.IResponse;
import com.cloud.rxbus.http.def.DefaultResponse;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;

/**
 * @author xb.zou
 * @date 2017/10/26
 * @option POST请求方法处理器
 */
public class PostExecutor implements IExecutor {
    private OkHttpClient mClient;

    public PostExecutor(OkHttpClient client) {
        this.mClient = client;
    }

    public IResponse execute(IRequest request) {
        //设置请求方法
        request.setMethod(new PostMethod());
        //解析IRequest
        Request.Builder builder = new Request.Builder();
        //设置请求头
        Map<String, String> headers = request.getHeader();
        for (String key: headers.keySet()){
            builder.header(key, headers.get(key));
        }
        //设置请求体
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(mediaType, request.getBody().toString());
        builder.post(body);
        //设置url
        builder.url(request.getUrl());

        //组装完成
        Request okRequest = builder.build();

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
