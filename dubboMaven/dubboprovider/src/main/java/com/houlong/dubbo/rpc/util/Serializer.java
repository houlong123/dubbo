package com.houlong.dubbo.rpc.util;

import com.alibaba.com.caucho.hessian.io.Hessian2Input;
import com.alibaba.com.caucho.hessian.io.Hessian2Output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by houlong on 2018/5/15.
 */
public class Serializer {

    /**
     * 序列化
     * @param o
     * @return
     * @throws IOException
     */
    public static byte[] serialize(Object o) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Hessian2Output out = new Hessian2Output(bos);
        out.writeObject(o);
        out.close();
        return bos.toByteArray();
    }

    /**
     * 反序列化
     * @param data
     * @return
     * @throws IOException
     */
    public static Object deserialize(byte[] data) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        Hessian2Input input = new Hessian2Input(bis);
        return input.readObject();
    }
}
