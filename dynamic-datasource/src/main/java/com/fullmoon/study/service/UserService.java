package com.fullmoon.study.service;

import com.fullmoon.study.model.Addr;
import com.fullmoon.study.model.User;
import com.fullmoon.study.model.UserInfo;

public interface UserService {

    User queryUser(Integer id);

    Integer insertUser(User user);

    Addr queryAddr(Integer id);

    Integer insertAddr(Addr addr);

    UserInfo queryUserInfo(Integer id);

    UserInfo insertUserInfo(UserInfo userInfo);

}
