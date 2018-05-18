package com.houlong.dubbo.rpc.additiona;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.google.common.collect.Lists;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by houlong on 2018/5/14.
 */
public class ServiceProviderManager {

    private static Map<String, Set<ProviderInfo>> serviceServerMap = new ConcurrentHashMap<String, Set<ProviderInfo>>();

    static {
        Set<ProviderInfo> set = new HashSet<ProviderInfo>();

        set.add(new ProviderInfo("127.0.0.1", 8080));
        serviceServerMap.put("com.houlong.dubbo.service.HelloService", set);
    }

    public static ProviderInfo getProvider(String serviceName) {
        Set<ProviderInfo> providerInfoSet = serviceServerMap.get(serviceName);

        if (CollectionUtils.isEmpty(providerInfoSet)) {
            throw new RuntimeException("no service provider found in registry");
        }

        List<ProviderInfo> providerInfoList = Lists.newArrayList(providerInfoSet);
        int randomIndex = new Random().nextInt(providerInfoList.size());
        return providerInfoList.get(randomIndex);
    }
}
