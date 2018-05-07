package com.houlong.dubbo.service.impl;

import com.houlong.dubbo.service.UserService;

/**
 * 用户服务接口
 * @author houlong
 * @version 0.0.1
 */
public class UserServiceImpl implements UserService {


    public String insert(String name) {
        return "插入成功 " + name;
    }

    public boolean delete(String name) {
        return false;
    }

    public String select(String name) {
        return "查询成功 " + name;
    }

    public int update(String name) {
        return 1;
    }
}
