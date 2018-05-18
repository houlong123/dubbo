package com.houlong.dubbo.rpc.proxy;


import com.google.common.reflect.Reflection;

/**
 * 在客户端模拟生成代理类
 */
public class ServiceProxyBeanFactory {

    public static Object getService(String serviceName) throws ClassNotFoundException {
        Class<?> serviceClass = Class.forName(serviceName);

        return Reflection.newProxy(serviceClass, new ServiceProxy(serviceName));
    }
}
