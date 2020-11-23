package com.fullmoon.study.service;

import com.fullmoon.study.mapper.UserMapper;
import com.fullmoon.study.model.Addr;
import com.fullmoon.study.model.User;
import com.fullmoon.study.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryUser(Integer id) {
        return userMapper.queryUser(id);
    }

    @Override
    public Integer insertUser(User user) {
        int result = userMapper.insertUser(user);
        return result > 0 ? user.getId() : -1;
    }

    @Override
    public Addr queryAddr(Integer id) {
        return userMapper.queryAddr(id);
    }

    @Override
    public Integer insertAddr(Addr addr) {
        int result = userMapper.insertAddr(addr);
        return result > 0 ? addr.getId() : -1;
    }

    @Override
    public UserInfo queryUserInfo(Integer id) {
        User user = userMapper.queryUser(id);
        Addr addr = userMapper.queryAddr(id);
        UserInfo userInfo = new UserInfo();
        userInfo.setAddr(addr);
        userInfo.setUser(user);
        return userInfo;
    }

    @Override
    public UserInfo insertUserInfo(UserInfo userInfo) {
        userMapper.insertAddr(userInfo.getAddr());
        userMapper.insertUser(userInfo.getUser());
        return userInfo;
    }
}
