package com.fullmoon.study.config;

import com.fullmoon.study.filter.HelloWorldFilter;
import com.fullmoon.study.servlet.HelloWorldServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jingping.liu
 * @date 2020-11-30
 * @description Servlet 和 Filter 配置类
 */
@Configuration
public class WebConfig {

    @Bean
    public ServletRegistrationBean<HelloWorldServlet> helloWorldServlet() {
        ServletRegistrationBean<HelloWorldServlet> servlet = new ServletRegistrationBean<>();
        servlet.addUrlMappings("/hello");
        servlet.setServlet(new HelloWorldServlet());
        return servlet;
    }

    @Bean
    public FilterRegistrationBean<HelloWorldFilter> helloWorldFilter() {
        FilterRegistrationBean<HelloWorldFilter> filter = new FilterRegistrationBean<>();
        filter.addUrlPatterns("/hello/*");
        filter.setFilter(new HelloWorldFilter());
        return filter;
    }
}
