package com.houlong.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * 用户服务启动类
 * @author houlong
 * @version 0.0.1
 */
public class Provider {

    public static void main( String[] args ) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"dubbo-provider.xml"});
        context.start();
        System.in.read();  // 按任意键退出
    }
}
