package com.fullmoon.study.service.impl;

import com.fullmoon.study.dao.UserMapper;
import com.fullmoon.study.entity.User;
import com.fullmoon.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryUserInfo(Integer id) {
        return userMapper.queryUserInfo(id);
    }

    @Override
    public List<User> queryUserByName(String name) {
        return userMapper.queryUserByName(name);
    }

    @Override
    public Integer insertUser(User user) {
        int result = userMapper.insertUser(user);
        return result > 0 ? user.getId() : -1;
    }
}
