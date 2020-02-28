package com.cloud.rxbus.bus;

/**
 * @author xb.zou
 * @date 2017/10/26
 * @option
 */
public class TestData {
    private String text;
    private String title;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "TestData{" +
                "text='" + text + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
