package com.cloud.rxbus.http;

import com.cloud.rxbus.http.component.HttpClient;
import com.cloud.rxbus.http.component.IRequest;
import com.cloud.rxbus.http.component.IResponse;
import com.cloud.rxbus.http.def.DefaultRequest;
import com.cloud.rxbus.xeps.okhttp.GetExecutor;
import com.cloud.rxbus.xeps.okhttp.GetMethod;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author xb.zou
 * @date 2017/10/26
 * @option
 */
public class GetTest {
    @Test
    public void testHttpGet() {
        OkHttpClient okHttpClient = new OkHttpClient();
        GetExecutor getExecutor = new GetExecutor(okHttpClient);

        HttpClient.getInstance().registerExecutor(new GetMethod(), getExecutor);
        IRequest request = new DefaultRequest("http://httpbin.org/get?name=Kyle");
        IResponse response = HttpClient.getInstance().execute(new GetMethod(), request);

        Assert.assertEquals(response.getCode(), 200);
        Root root = new Gson().fromJson(response.getData(), Root.class);
        Assert.assertEquals(root.getArgs().getName(), "Kyle");
    }

    @Test
    public void testHttpGet2() {
        OkHttpClient okHttpClient = new OkHttpClient();
        GetExecutor getExecutor = new GetExecutor(okHttpClient);

        HttpClient.getInstance().registerExecutor(new GetMethod(), getExecutor);
        IRequest request = new DefaultRequest("http://httpbin.org/get?name=${name}");
        request.setBody("name", "Kyle");

        IResponse response = HttpClient.getInstance().execute(new GetMethod(), request);

        Assert.assertEquals(response.getCode(), 200);
        Root root = new Gson().fromJson(response.getData(), Root.class);
        Assert.assertEquals(root.getArgs().getName(), "Kyle");
    }
}
