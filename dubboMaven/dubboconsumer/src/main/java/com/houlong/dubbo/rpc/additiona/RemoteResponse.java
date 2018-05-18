package com.houlong.dubbo.rpc.additiona;

import java.io.Serializable;

/**
 * Created by houlong on 2018/5/14.
 */
public class RemoteResponse implements Serializable {

    private String requestId;
    private int responseCode;
    private Object responseValue;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public Object getResponseValue() {
        return responseValue;
    }

    public void setResponseValue(Object responseValue) {
        this.responseValue = responseValue;
    }
}
