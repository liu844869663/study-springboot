package com.fullmoon.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringCacheRemoteProviderApplication {
    public static void main(String[] args){
        SpringApplication.run(SpringCacheRemoteProviderApplication.class, args);
    }
}
