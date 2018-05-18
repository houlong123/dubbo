package com.houlong.dubbo.rpc.util;

import com.houlong.dubbo.rpc.RemoteServiceServer;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.lang.reflect.Method;

/**
 * 服务端处理
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<RemoteRequest> {


    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RemoteRequest remoteRequest) throws Exception {
        //收到了request，从启动时加载好的map中找到request请求中的serviceImpl实例
        Object actualServiceImpl = RemoteServiceServer.getActualServiceImpl(remoteRequest.getServiceName());
        if (actualServiceImpl != null) {
            //根据request中的方法名，参数类型，参数信息，反射调用
            Class<?> serviceInterface = actualServiceImpl.getClass();
            Method method = serviceInterface.getMethod(remoteRequest.getMethodName(), remoteRequest.getParameterTypes());

            //反射计算结果
            Object result = method.invoke(actualServiceImpl, remoteRequest.getArguments());

            //构造response
            RemoteResponse remoteResponse = new RemoteResponse();
            remoteResponse.setRequestId(remoteRequest.getRequestId());
            remoteResponse.setResponseValue(result);
            remoteResponse.setResponseCode(1);
            channelHandlerContext.writeAndFlush(remoteResponse).addListener(ChannelFutureListener.CLOSE);
        }
    }
}
