package com.fullmoon.study.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alicp.jetcache.Cache;
import com.alicp.jetcache.RefreshPolicy;
import com.alicp.jetcache.anno.*;
import com.alicp.jetcache.embedded.CaffeineCacheBuilder;
import com.fullmoon.study.entity.Child;
import com.fullmoon.study.entity.People;
import com.fullmoon.study.service.ChildService;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service(
        version = "${study.service.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}",
        timeout = 10000
)
public class ChildServiceImpl implements ChildService {

    @CreateCache(name = "childCache" , localLimit = 50 ,cacheType = CacheType.LOCAL)
    @CachePenetrationProtect
    public Cache<String, Child> childCache;

    private Cache<Integer, String> exampleCache;

    @PostConstruct
    public void init(){
        RefreshPolicy policy = RefreshPolicy.newPolicy(60, TimeUnit.MINUTES)
                .stopRefreshAfterLastAccess(120, TimeUnit.MINUTES);
        childCache.config().setLoader(name -> {
            Child child = new Child();
            child.setName(name);
            return child;
        });
        childCache.config().setRefreshPolicy(policy);

        exampleCache = CaffeineCacheBuilder.createCaffeineCacheBuilder()
                .limit(10)
                .expireAfterWrite(60, TimeUnit.SECONDS)
                .buildCache();
    }

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
        List<Child> children = people.getChildren();
        if (!CollectionUtils.isEmpty(children)){
            for (Child child : children){
                childCache.put(child.getName(), child);
            }
        }
        return children;
    }

    @Override
    public Child getChildByName(String name) {
        Child defaultChild = new Child();
        return childCache.computeIfAbsent(name , (k) -> defaultChild);
    }
}
