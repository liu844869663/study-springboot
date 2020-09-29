package com.fullmoon.study.dao;

import com.fullmoon.study.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    User queryUserInfo(@Param("id") Integer id);

    List<User> queryUserByName(@Param("name") String Name);

    int insertUser(User user);

    List<User> queryUserByIds(List<Integer> ids);


}
