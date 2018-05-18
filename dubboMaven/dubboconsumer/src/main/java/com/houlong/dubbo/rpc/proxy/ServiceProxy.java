package com.houlong.dubbo.rpc.proxy;

import com.houlong.dubbo.rpc.additiona.ProviderInfo;
import com.houlong.dubbo.rpc.additiona.RemoteClient;
import com.houlong.dubbo.rpc.additiona.RemoteRequest;
import com.houlong.dubbo.rpc.additiona.RemoteResponse;
import com.houlong.dubbo.rpc.additiona.ServiceProviderManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * Created by houlong on 2018/5/14.
 */
public class ServiceProxy implements InvocationHandler {

    private String serviceName;

    public ServiceProxy(String serviceName) {
        this.serviceName = serviceName;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println(serviceName);
        //从注册中心获取服务地址
        ProviderInfo providerInfo = ServiceProviderManager.getProvider(serviceName);

        // 构造远程调用的request
        RemoteRequest request = new RemoteRequest();
        request.setArguments(args);
        request.setMethodName(method.getName());
        request.setParameterTypes(method.getParameterTypes());
        request.setRequestId(UUID.randomUUID().toString());
        request.setServiceName(serviceName);

        //RPC请求
        RemoteClient client = new RemoteClient(providerInfo);
        RemoteResponse response = client.send(request);

        return response.getResponseValue();
    }
}
