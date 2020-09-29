package com.fullmoon.study.service;

import com.fullmoon.study.entity.User;

import java.util.List;

public interface UserService {

    public User queryUserInfo(Integer id);

    public List<User> queryUserByName(String name);

    public Integer insertUser(User user);
}
