package com.fullmoon.study.dao.test;

import com.alibaba.fastjson.JSON;
import com.fullmoon.study.Application;
import com.fullmoon.study.dao.UserMapper;
import com.fullmoon.study.entity.User;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void testQueryUserByIds(){
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        List<User> users = userMapper.queryUserByIds(ids);
        if(!CollectionUtils.isEmpty(users)){
            log.info("result:{}", JSON.toJSONString(users));
        }
    }


    @Test
    public void testQueryUserByName(){
        PageHelper.startPage(2, 2);
        Page<User> pages = new Page<>();
        pages = (Page<User>) userMapper.queryUserByName("Ming");
        List<User> result = userMapper.queryUserByName("Ming");
        if(!CollectionUtils.isEmpty(result)){
            for(User user : result){
                log.info(JSON.toJSONString(user));
            }
        }
        if(!CollectionUtils.isEmpty(pages)){
            log.info(JSON.toJSONString(pages.getResult()));
            log.info(pages.getTotal());
        }
    }
}
