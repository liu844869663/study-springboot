package com.fullmoon.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations = {"classpath:spring-cache.xml"})
@EnableCaching // 开启缓存注解
public class SpringCacheProviderLocalApplication {
    public static void main(String[] args){
        SpringApplication.run(SpringCacheProviderLocalApplication.class, args);
    }
}
