package com.fullmoon.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EnableAutoConfiguration
@ImportResource(locations = {"classpath:dubbo-consumer.xml"})
public class SpringCacheConsumerApplication {
    public static void main(String[] args){
        SpringApplication.run(SpringCacheConsumerApplication.class, args);
    }
}
