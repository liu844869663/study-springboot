package com.fullmoon.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource(locations = {"classpath:dubbo-consumer.xml"})
@EnableAutoConfiguration
@SpringBootApplication
public class JetCacheConsumerApplication {
    public static void main(String[] args){
        SpringApplication.run(JetCacheConsumerApplication.class, args);
    }
}
