package com.houlong.dubbo.rpc.additiona;

/**
 * Created by houlong on 2018/5/14.
 */
public class ProviderInfo {

    private String address;
    private int port;

    public ProviderInfo(String address, int port) {
        this.address = address;
        this.port = port;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
