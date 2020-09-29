package com.fullmoon.study.controller;


import com.fullmoon.study.entity.Result;
import com.fullmoon.study.entity.User;
import com.fullmoon.study.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/hello", method = {RequestMethod.GET})
    public String hello(String name){
        log.info("name:{}", name);
        return "Hello, " + name;
    }

    @RequestMapping(value = "/queryUserInfo", method = {RequestMethod.GET})
    public Result<User> queryUser(Integer id){
        try{
            return Result.success().data(userService.queryUserInfo(id));
        } catch (Exception e){
            return Result.fail(e);
        }

    }
    @RequestMapping(value = "/queryUserByName", method = {RequestMethod.GET})
    public Result<List<User>> queryUser(String name) {
        try {
            return Result.success().data(userService.queryUserByName(name));
        } catch (Exception e) {
            return Result.fail(e);
        }
    }

    @RequestMapping(value = "/addUser", method = {RequestMethod.GET})
    public Result<Integer> addUser(User user){
        try {
            return Result.success().data(userService.insertUser(user));
        } catch (Exception e) {
            return Result.fail(e);
        }
    }
}
