package com.cloud.rxbus.bus;

import org.junit.Assert;

/**
 * @author xb.zou
 * @date 2017/10/26
 * @option
 */
public class BusAnother {
    public BusAnother() {
        Bus.getInstance().registerSubscriber(this);
    }

    @RegisterBus
    public void test(TestData data) {
        Assert.assertEquals(data.getText(), "The Bus is success!");
        Assert.assertEquals(data.getTitle(), "Title");
    }
}
