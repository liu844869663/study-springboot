package com.fullmoon.study.datasource.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.fullmoon.study.datasource.DataSourceContextHolder;

/**
 * @author jingping.liu
 * @date 2020-11-23
 * @description 数据源注解，可以用于 Method, Class, interface (including annotation type), or enum declaration
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface TargetDataSource {

    /**
     * 指定数据源 {@link DataSourceContextHolder}
     *
     * @return 数据源的 Key 值
     */
    String value() default "master";
}
