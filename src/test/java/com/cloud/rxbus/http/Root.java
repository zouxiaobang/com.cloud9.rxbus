package com.cloud.rxbus.http;

/**
 * @author xb.zou
 * @date 2017/10/26
 * @option
 */
public class Root {
    private Args args;

//    private Headers headers;

    private String origin;

    private String url;

    public void setArgs(Args args){
        this.args = args;
    }
    public Args getArgs(){
        return this.args;
    }
    /*public void setHeaders(Headers headers){
        this.headers = headers;
    }
    public Headers getHeaders(){
        return this.headers;
    }*/
    public void setOrigin(String origin){
        this.origin = origin;
    }
    public String getOrigin(){
        return this.origin;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public String getUrl(){
        return this.url;
    }
}
