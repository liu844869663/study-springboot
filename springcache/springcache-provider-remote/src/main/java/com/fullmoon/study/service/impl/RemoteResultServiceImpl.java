package com.fullmoon.study.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.fullmoon.study.entity.Param;
import com.fullmoon.study.entity.Result;
import com.fullmoon.study.service.RemoteResultService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

@Service(
        application = "${dubbo.application.id}",
        registry = "${dubbo.registry.id}",
        protocol = "${dubbo.protocol.id}",
        version = "${study.service.version}",
        timeout = 10000
)
@CacheConfig(cacheNames = "RemoteResultService") // 统一配置本类的缓存注解的属性
public class RemoteResultServiceImpl implements RemoteResultService {

    /**
     * Cacheable注解的相关配置：
     * 1.value：指定缓存名称，至少一个，多个使用{}
     * 2.key：设置缓存的key，缺省按照方法的所有参数进行组合
     * 3.condition：缓存条件，true 才进行缓存/清除缓存
     * 4.unless：否定缓存，true不进行缓存
     * 另外CacheEvict 注解为清空所有缓存
     * 具体可参考：https://www.cnblogs.com/yueshutong/p/9381540.html
     */
    @Cacheable(keyGenerator = "defaultKeyGenerator") // 主要针对方法配置，能够根据方法的请求参数对其进行缓存
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
