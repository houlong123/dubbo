package com.houlong.dubbo.rpc;

/**
 * Created by houlong on 2018/5/15.
 */
public class RemoteServiceBean {

    private String serviceName;
    private Object serviceImpl;

    public void init() {
        System.out.println("服务信息加载 " + serviceName + " = " + serviceImpl);
        RemoteServiceServer.addService(serviceName, serviceImpl);
        System.out.println("haole");
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Object getServiceImpl() {
        return serviceImpl;
    }

    public void setServiceImpl(Object serviceImpl) {
        this.serviceImpl = serviceImpl;
    }
}
