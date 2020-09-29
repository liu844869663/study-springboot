package com.fullmoon.study.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@Configuration
public class RedisCacheConfig extends CachingConfigurerSupport {

    /**
     * 自定义一个缓存Key的生成器
     *
     * @return Key生成器
     */
    @Bean("defaultKeyGenerator")
    public KeyGenerator simpleKeyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            String className = target.getClass().getName();
            String serviceName = className.substring(className.lastIndexOf(".") + 1);
            sb.append(serviceName);
            sb.append(".");
            sb.append(method.getName());
            sb.append("[");
            for (Object obj : params) {
                sb.append(JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue));
            }
            sb.append("]");
            return sb.toString();
        };
    }

    @Bean
    CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        return new RedisCacheManager(
                RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory),
                // 默认策略,配置key的失效时间
                this.getRedisCacheConfigurationWithTtl(600L),
                // 配置指定缓存实例的失效时间
                this.getRedisCacheConfigurationMap()
        );
    }

    /**
     * 配置指定缓存实例的失效时间
     *
     * @return 缓存实例的配置策略
     */
    private Map<String, RedisCacheConfiguration> getRedisCacheConfigurationMap() {
        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
        redisCacheConfigurationMap.put("RemoteResultService", this.getRedisCacheConfigurationWithTtl(3600L));
        return redisCacheConfigurationMap;
    }

    /**
     * 默认策略,配置key的失效时间
     *
     * @param seconds 时间长度
     * @return 缓存实例的配置策略
     */
    private RedisCacheConfiguration getRedisCacheConfigurationWithTtl(Long seconds) {
        // 定义序列化方式
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer);
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        // 设置CacheManager的值序列化方式为jackson2JsonRedisSerializer;
        // 默认就是使用StringRedisSerializer序列化key，JdkSerializationRedisSerializer序列化value
        redisCacheConfiguration = redisCacheConfiguration.serializeValuesWith(pair).
                entryTtl(Duration.ofSeconds(seconds));

        return redisCacheConfiguration;
    }
}
