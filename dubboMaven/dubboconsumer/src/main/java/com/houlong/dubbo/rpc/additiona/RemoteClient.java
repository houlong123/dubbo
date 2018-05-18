package com.houlong.dubbo.rpc.additiona;

import com.google.common.util.concurrent.SettableFuture;
import com.houlong.dubbo.rpc.util.NettyDecoder;
import com.houlong.dubbo.rpc.util.NettyEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.TimeUnit;

/**
 * Created by houlong on 2018/5/14.
 */
public class RemoteClient {

    //远程服务信息
    private ProviderInfo providerInfo;

    public RemoteClient(ProviderInfo providerInfo) {
        this.providerInfo = providerInfo;
    }

    public RemoteResponse send(RemoteRequest request) {
        final SettableFuture<RemoteResponse> future = SettableFuture.create();

        //启动netty链接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(bossGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        //处理我们业务逻辑的ChannelHandler
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new NettyDecoder(), new NettyEncoder(), new NettyClientHandler(future));
                        }
                    });

            //链接netty
            ChannelFuture f = bootstrap.connect(providerInfo.getAddress(), providerInfo.getPort()).sync();
            //发送request
            ChannelFuture writeFuture = f.channel().writeAndFlush(request);
            return future.get();

        } catch (Exception e) {
            System.out.println("出错啦：" + e.getMessage());
        } finally {
            bossGroup.shutdownGracefully();
        }

        return null;
    }
}
