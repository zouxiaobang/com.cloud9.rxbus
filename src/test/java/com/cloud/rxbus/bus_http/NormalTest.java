package com.cloud.rxbus.bus_http;

import com.cloud.rxbus.bus.Bus;
import com.cloud.rxbus.bus.RegisterBus;
import com.cloud.rxbus.bus.TestData;
import com.cloud.rxbus.http.Root;
import com.cloud.rxbus.http.component.HttpClient;
import com.cloud.rxbus.http.component.IRequest;
import com.cloud.rxbus.http.component.IResponse;
import com.cloud.rxbus.http.def.DefaultRequest;
import com.cloud.rxbus.xeps.okhttp.GetExecutor;
import com.cloud.rxbus.xeps.okhttp.GetMethod;
import com.google.gson.Gson;
import io.reactivex.functions.Function;
import okhttp3.OkHttpClient;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author xb.zou
 * @date 2017/10/26
 * @option
 */
public class NormalTest {
    @Test
    public void testBusWithHttp() {
        Bus.getInstance().registerSubscriber(this);
        Bus.getInstance().executeProcess(new Function() {
            public Object apply(Object o) throws Exception {
                OkHttpClient okHttpClient = new OkHttpClient();
                GetExecutor getExecutor = new GetExecutor(okHttpClient);

                HttpClient.getInstance().registerExecutor(new GetMethod(), getExecutor);
                IRequest request = new DefaultRequest("http://httpbin.org/get?name=${name}");
                request.setBody("name", "Kyle");

                IResponse response = HttpClient.getInstance().execute(new GetMethod(), request);

                Assert.assertEquals(response.getCode(), 200);
                Root root = new Gson().fromJson(response.getData(), Root.class);
                return root;
            }
        });
    }

    @RegisterBus
    public void testResponse(Root root) {
        Assert.assertEquals(root.getArgs().getName(), "Kyle");
    }
}
