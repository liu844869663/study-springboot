package com.fullmoon.study.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.fullmoon.study.entity.Param;
import com.fullmoon.study.entity.Result;
import com.fullmoon.study.service.LocalResultService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

@Service(
        application = "${dubbo.application.id}",
        registry = "${dubbo.registry.id}",
        protocol = "${dubbo.protocol.id}",
        version = "${study.service.version}",
        timeout = 10000
)
@CacheConfig(cacheNames = {"LocalResultService"})
public class LocalResultServiceImpl implements LocalResultService {

    /*1. @Cacheable的几个属性详解：
     *       cacheNames/value：指定缓存组件的名字
     *       key：缓存数据使用的key,可以用它来指定。默认使用方法参数的值，一般不需要指定
     *       keyGenerator：作用和key一样，二选一
     *       cacheManager和cacheResolver作用相同：指定缓存管理器，二选一
     *       condition：指定符合条件才缓存，比如：condition="#id>3"
     *                   也就是说传入的参数id>3才缓存数据
     *      unless：否定缓存，当unless为true时不缓存，可以获取方法结果进行判断
     *      sync：是否使用异步模式*/
    //@Cacheable(cacheNames= "person")
    //@Cacheable(cacheNames= "person",key="#id",condition="#id>3")
    @Cacheable
    @Override
    public Result getResult(Param param) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        Result result = new Result();
        result.setResult(param.getMessage() + ":" + param.getStart() + "-" + param.getEnd());
        return result;
    }
}
