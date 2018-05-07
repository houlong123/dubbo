package com.houlong.dubbo.service.impl;

import com.houlong.dubbo.service.OrderService;

/**
 * 订单服务接口
 * @author houlong
 * @version 0.0.1
 */
public class OrderServiceImpl implements OrderService {

    public String insert(String name) {
        return "下单成功 " + name;
    }

    public boolean delete(String name) {
        return false;
    }

    public String select(String name) {
        return "查询订单成功 " + name;
    }

    public int update(String name) {
        return 0;
    }
}
