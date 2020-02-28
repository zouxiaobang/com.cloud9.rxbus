package com.cloud.rxbus.http.def;

import com.cloud.rxbus.http.component.IMethod;
import com.cloud.rxbus.http.component.IRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xb.zou
 * @date 2017/10/26
 * @option 默认实现的Request类
 */
public class DefaultRequest implements IRequest {
    /**
     * 是否开启联想功能
     */
    private boolean isGuess = true;
    private IMethod mMethod;
    private Map<String, String> mHeaders = new HashMap<String, String>();
    private Map<String, Object> mBodys = new HashMap<String, Object>();
    private String mUrl;

    public DefaultRequest(String url){
        this.mUrl = url;
    }

    public void setHeader(String key, String value) {
        mHeaders.put(key, value);
    }

    public void setBody(String key, Object value) {
        mBodys.put(key, value);
    }

    public void setMethod(IMethod method) {
        this.mMethod = method;
    }

    public Map<String, String> getHeader() {

        return mHeaders;
    }

    public String getUrl() {
        if ("GET".equals(guessMethod())){
            for (String key: mBodys.keySet()){
                // http://httpbin.org/get?name=${name}
                // bodys {
                //  "name":"Kyle"
                // }
                mUrl = mUrl.replace("${" + key + "}", mBodys.get(key).toString());
            }
        }
        return mUrl;
    }

    private String guessMethod() {
        if (isGuess && mMethod.getTypeText().toLowerCase().contains("get")) {
            return "GET";
        }
        return null;
    }

    public Object getBody() {
        return mBodys;
    }

    public void setGuess(boolean guess) {
        isGuess = guess;
    }
}
