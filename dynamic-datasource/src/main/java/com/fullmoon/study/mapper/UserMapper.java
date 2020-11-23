package com.fullmoon.study.mapper;

import com.fullmoon.study.datasource.annotation.TargetDataSource;
import com.fullmoon.study.model.Addr;
import com.fullmoon.study.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    User queryUser(@Param("id") Integer id);

    int insertUser(User user);

    @TargetDataSource("slave")
    Addr queryAddr(@Param("id") Integer id);

    @TargetDataSource("slave")
    int insertAddr(Addr addr);

}
