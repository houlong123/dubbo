package com.houlong.dubbo.rpc.util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by houlong on 2018/5/15.
 */
public class NettyDecoder extends ByteToMessageDecoder {

    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> list) throws Exception {
        //收到的字节不足一个int，即Encode阶段写入的数据总长度
        if (in.readableBytes() < 4) {
            System.out.println("no enough readable bytes");
            return;
        }

        //接收的字节到达4个，提取一个int，即期望接收的数据总长度
        int dataLenth = in.readInt();
        if (dataLenth < 0) {
            channelHandlerContext.close();
        }

        //接收的字节流除去int剩余的字节长度还未达到期望的长度，表示数据未接收完整
        if (in.readableBytes() < dataLenth) {
            in.resetReaderIndex();
        }

        //长度达到了，已经足够，读取出完整的数据
        byte[] data = new byte[dataLenth];
        in.readBytes(data);

        //反序列化
        Object object = Serializer.deserialize(data);
        //当decode中把一个对象加入到out中，代表已经解析成功了，之后decode不再被调用
        list.add(object);

    }
}
