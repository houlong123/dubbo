package com.houlong.dubbo.rpc.additiona;

import com.google.common.util.concurrent.SettableFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by houlong on 2018/5/15.
 */
public class NettyClientHandler extends SimpleChannelInboundHandler<RemoteResponse> {

    private SettableFuture<RemoteResponse> future;

    public NettyClientHandler(SettableFuture<RemoteResponse> future) {
        this.future = future;
    }


    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RemoteResponse remoteResponse) throws Exception {
        future.set(remoteResponse);
    }
}
