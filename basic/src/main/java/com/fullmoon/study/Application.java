package com.fullmoon.study;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.fullmoon.study.dao")
@EnableEncryptableProperties
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
