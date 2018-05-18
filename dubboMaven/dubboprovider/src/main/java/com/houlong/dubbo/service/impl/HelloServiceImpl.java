package com.houlong.dubbo.service.impl;

import com.houlong.dubbo.service.HelloService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by houlong on 2018/5/14.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class HelloServiceImpl implements HelloService {

    public String sayHello(String name) {
        return "Hello " + name;
    }
}
