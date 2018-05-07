package com.houlong.dubbo.service;

/**
 * 用户服务接口
 * @author houlong
 * @version 0.0.1
 */
public interface UserService {

    public String insert(String name);

    public boolean delete(String name);

    public String select(String name);

    public int update(String name);
}
