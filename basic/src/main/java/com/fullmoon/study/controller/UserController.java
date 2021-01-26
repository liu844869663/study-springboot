package com.fullmoon.study.controller;


import com.fullmoon.study.entity.Result;
import com.fullmoon.study.entity.User;
import com.fullmoon.study.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/queryUserInfo")
    public Result<User> queryUser(@RequestParam("id") Integer id)  {
        try {
            return Result.success().data(userService.queryUserInfo(id));
        } catch (Exception e) {
            return Result.fail(e);
        }
    }

    @GetMapping(value = "/queryUserByName")
    public Result<List<User>> queryUser(@RequestBody String name) {
        try {
            return Result.success().data(userService.queryUserByName(name));
        } catch (Exception e) {
            return Result.fail(e);
        }
    }

    @PostMapping("/addUser")
    public Result<Integer> addUser(@RequestBody User user) {
        try {
            return Result.success().data(userService.insertUser(user));
        } catch (Exception e) {
            return Result.fail(e);
        }
    }
}
