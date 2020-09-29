package com.fullmoon.study.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alicp.jetcache.anno.CachePenetrationProtect;
import com.alicp.jetcache.anno.CacheRefresh;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.fullmoon.study.entity.Child;
import com.fullmoon.study.entity.People;
import com.fullmoon.study.service.ChildService;

import java.util.List;

@Service(
        version = "${study.service.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}",
        timeout = 10000
)
public class ChildServiceImpl implements ChildService {

    @Override
    @Cached(name = "ChildService.getChildren", expire = 300, localLimit = 50, cacheType = CacheType.BOTH)
    @CacheRefresh(refresh = 60, stopRefreshAfterLastAccess = 60 * 2)
    @CachePenetrationProtect
    public List<Child> getChildren(People people) {
        try{
            Thread.sleep(5000);
        }catch(Exception e) {
            Thread.currentThread().interrupt();
        }
        return people.getChildren();
    }
}
