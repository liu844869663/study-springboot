package com.fullmoon.study.util;

import com.alibaba.fastjson.JSON;
import com.fullmoon.study.entity.User;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Log4j2
public class StreamTest {

    public static void filter(){
        List<User> users = new ArrayList<>();
        List<User> result = new ArrayList<>();
        users.add(new User(1,"小明", 20, "男", "1", "1" ,"1"));
        users.add(new User(2,"小华", 20, "男", "2", "2" ,"2"));
        users.add(new User(3,"小美", 20, "女", "3", "3" ,"3"));
        // 根据过滤条件过滤List几个中的元素
        result = users.stream().filter(user -> {
            return (user.getId() > 1) ? true :false;
        }).collect(Collectors.toList());
        log.info(JSON.toJSONString(result));
        // 根据过滤条件统计List集合中符合条件的元素的个数
        long count = users.stream().filter(user -> {
            return (user.getId() > 1) ? true :false;
        }).count();
        log.info("count:{}", count);
    }

    public static void map(){
        List<User> users = new ArrayList<>();
        List<User> result = new ArrayList<>();
        users.add(new User(1,"小明", 20, "男", "1", "1" ,"1"));
        users.add(new User(2,"小华", 20, "男", "2", "2" ,"2"));
        users.add(new User(3,"小美", 20, "女", "3", "3" ,"3"));
        // 循环遍历List并为每个元素赋新值
        result = users.stream().map( user -> {
            return new User(1,"小明", 20, "男", "1", "1" ,"1");
        }).collect(Collectors.toList());
        log.info(JSON.toJSONString(result));
    }


    public static void main(String[] args){
        // StreamTest.filter();
        // StreamTest.map();
    }
}
