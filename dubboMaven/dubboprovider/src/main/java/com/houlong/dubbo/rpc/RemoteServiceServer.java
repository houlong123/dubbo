package com.houlong.dubbo.rpc;

import com.houlong.dubbo.rpc.util.NettyDecoder;
import com.houlong.dubbo.rpc.util.NettyEncoder;
import com.houlong.dubbo.rpc.util.NettyServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by houlong on 2018/5/15.
 */
public class RemoteServiceServer {

    private static volatile boolean started = false;

    private static ConcurrentHashMap<String, Object> serviceImplMap = new ConcurrentHashMap<String, Object>();

    /**
     * 启动netty服务
     */
    static {
        bootstrap();
    }

    private static void bootstrap() {
        if (!started) {
            synchronized (RemoteServiceServer.class) {
                if (!started) {
                    doStartup();
                }
            }
        }
    }

    private static void doStartup() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new NettyDecoder(), new NettyEncoder(), new NettyServerHandler());
                    }
                }).option(ChannelOption.SO_BACKLOG, 128)
        .childOption(ChannelOption.SO_KEEPALIVE, true);

        try {
            ChannelFuture future = bootstrap.bind(8080).sync();
            future.addListener(new ChannelFutureListener() {

                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (channelFuture.isSuccess()) {
                        started = true;
                        System.out.println("server started!");
                    }
                }
            });
        } catch (Exception e) {
            System.out.println("server started failed: " + e.getMessage());
        }
    }


    /**
     * 添加服务信息
     * @param serviceName
     * @param serviceImpl
     */
    public static void addService(String serviceName, Object serviceImpl) {
        serviceImplMap.putIfAbsent(serviceName, serviceImpl);
    }

    /**
     * 获取服务实例
     * @param serviceName
     * @return
     */
    public static Object getActualServiceImpl(String serviceName) {
        if (!started) {
            bootstrap();
        }
        return serviceImplMap.get(serviceName);
    }
}
