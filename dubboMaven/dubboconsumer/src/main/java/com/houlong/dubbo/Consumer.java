package com.houlong.dubbo;

import com.houlong.dubbo.service.OrderService;
import com.houlong.dubbo.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class Consumer {

    public static void main( String[] args ) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"dubbo-consumer.xml"});
        context.start();
        UserService userService = (UserService) context.getBean("userService");

        System.out.println("执行插入操作--------");
        String result = userService.insert("哈哈");
        System.out.println("执行插入结果-------- " + result);

        System.out.println("执行删除操作--------");
        boolean a = userService.delete("哈哈");
        System.out.println("执行删除结果-------- " + a);

        System.out.println("执行查询操作--------");
        result = userService.select("哈哈");
        System.out.println("执行查询结果-------- " + result);

        System.out.println("执行更新操作--------");
        int update = userService.update("哈哈");
        System.out.println("执行更新结果-------- " + update);

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("=============  订单开始  =================");
        OrderService orderService = (OrderService) context.getBean("orderService");
        System.out.println("执行下单操作--------");
        result = orderService.insert("哈哈");
        System.out.println("执行下单结果-------- " + result);

        System.out.println("执行删除操作--------");
        a = orderService.delete("哈哈");
        System.out.println("执行删除结果-------- " + a);

        System.out.println("执行查询订单操作--------");
        result = orderService.select("哈哈");
        System.out.println("执行查询订单结果-------- " + result);

        System.out.println("执行更新操作--------");
        update = orderService.update("哈哈");
        System.out.println("执行更新结果-------- " + update);

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
