package com.fullmoon.study.controller;

import com.fullmoon.study.entity.Param;
import com.fullmoon.study.entity.Result;
import com.fullmoon.study.service.LocalResultService;
import com.fullmoon.study.service.RemoteResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringCacheController {

    @Autowired
    private LocalResultService resultService;

    @Autowired
    private RemoteResultService redisResultService;

    @RequestMapping( value = "/getLocalResult", method = {RequestMethod.POST})
    public Result getResult(@RequestBody Param param){
        return resultService.getResult(param);
    }

    @RequestMapping( value = "/getRemoteResult", method = {RequestMethod.POST})
    public Result getRedisResult(@RequestBody Param param){
        return redisResultService.getResult(param);
    }
}
