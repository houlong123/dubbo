package com.houlong.dubbo.rpc.util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by houlong on 2018/5/15.
 */
public class NettyEncoder extends MessageToByteEncoder {

    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        //序列化
        byte[] data = Serializer.serialize(o);

        //先写数据长度
        byteBuf.writeInt(data.length);
        //写入数据字节流
        byteBuf.writeBytes(data);
    }
}
