package com.fullmoon.study.datasource;

import com.fullmoon.study.datasource.annotation.TargetDataSource;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author jingping.liu
 * @date 2020-11-23
 * @description 多数据源切面
 */
@Aspect
@Component
@Log4j2
public class DataSourceAspect {

    @Before("@annotation(com.fullmoon.study.datasource.annotation.TargetDataSource)")
    public void before(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        if (method.isAnnotationPresent(TargetDataSource.class)) {
            TargetDataSource targetDataSource = method.getAnnotation(TargetDataSource.class);
            DataSourceContextHolder.setDataSourceKey(targetDataSource.value());
            log.info("set the datasource of the current thread to [{}]", targetDataSource.value());
        } else if (joinPoint.getTarget().getClass().isAnnotationPresent(TargetDataSource.class)) {
            TargetDataSource targetDataSource = joinPoint.getTarget().getClass().getAnnotation(TargetDataSource.class);
            DataSourceContextHolder.setDataSourceKey(targetDataSource.value());
            log.info("set the datasource of the current thread to [{}]", targetDataSource.value());
        }
    }

    @After("@annotation(com.fullmoon.study.datasource.annotation.TargetDataSource)")
    public void after() {
        DataSourceContextHolder.clear();
        log.info("clear the datasource of the current thread");
    }

}
