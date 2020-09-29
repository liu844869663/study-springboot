package com.fullmoon.study.service.test;

import com.alibaba.fastjson.JSON;
import com.fullmoon.study.Application;
import com.fullmoon.study.entity.User;
import com.fullmoon.study.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    public void testQueryUserInfo(){
        User user = userService.queryUserInfo(1);
        if(user != null){
            log.info("result:{}", JSON.toJSONString(user));
        }
    }
}
