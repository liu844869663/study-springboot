package com.fullmoon.study.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author jingping.liu
 * @date 2020-11-30
 * @description 过滤器 Demo
 */
@WebFilter
public class HelloWorldFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("触发 Hello World 过滤器...");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
