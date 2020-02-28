package com.cloud.rxbus.bus;

import io.reactivex.functions.Function;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author xb.zou
 * @date 2017/10/26
 * @option
 */
public class BusTest {

    @Test
    public void testExecute() {
        BusAnother another = new BusAnother();

        Bus.getInstance().registerSubscriber(this);
        Bus.getInstance().executeProcess(new Function() {
            public Object apply(Object o) throws Exception {
                TestData testData = new TestData();
                testData.setText("The Bus is success!");
                testData.setTitle("Title");
                return testData;
            }
        });
    }

    @RegisterBus
    public void testResponse(TestData data) {
        Assert.assertEquals(data.getText(), "The Bus is success!");
        Assert.assertEquals(data.getTitle(), "Title");
    }
}
