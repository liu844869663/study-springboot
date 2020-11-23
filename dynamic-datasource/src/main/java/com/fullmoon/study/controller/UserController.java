package com.fullmoon.study.controller;

import com.fullmoon.study.model.Addr;
import com.fullmoon.study.model.User;
import com.fullmoon.study.model.UserInfo;
import com.fullmoon.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/query/user")
    public User queryUser(@RequestParam("id") Integer id) {
        return userService.queryUser(id);
    }

    @PostMapping(value = "/insert/user")
    public Integer addUser(@RequestBody User user) {
        return userService.insertUser(user);
    }

    @GetMapping(value = "/query/addr")
    public Addr queryAddr(@RequestParam("id") Integer id) {
        return userService.queryAddr(id);
    }

    @PostMapping(value = "/insert/addr")
    public Integer insertAddr(@RequestBody Addr addr) {
        return userService.insertAddr(addr);
    }

    @GetMapping(value = "/query/user/info")
    public UserInfo query(@RequestParam("id") Integer id){
        return userService.queryUserInfo(id);
    }

    @PostMapping(value = "/insert/user/info")
    public UserInfo insert(@RequestBody UserInfo userInfo){
        return userService.insertUserInfo(userInfo);
    }
}
