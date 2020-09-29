package com.fullmoon.study.controller;

import com.alibaba.fastjson.JSON;
import com.fullmoon.study.entity.Child;
import com.fullmoon.study.entity.People;
import com.fullmoon.study.service.ChildService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
public class ChildController {

    @Autowired
    ChildService childService;

    @RequestMapping(value = "/getChildren", method = {RequestMethod.POST})
    public List<Child> getChildren(@RequestBody People people){
        log.info("begin");
        if(people != null){
            log.info(JSON.toJSONString(people));
        }
        return childService.getChildren(people);
    }

}
