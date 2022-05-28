package com.firenut.store.config;

import com.firenut.store.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class LoginInterceptorConfig implements WebMvcConfigurer {



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        创建自定义的拦截器对象
        HandlerInterceptor interceptor=new LoginInterceptor();

//        配置白名单
        List<String>patterns=new ArrayList<>();
        patterns.add("/bootstrap3/**");
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/web/login.html");
        patterns.add("/web/register.html");
        patterns.add("/web/product.html");
        patterns.add("/users/reg");
        patterns.add("/users/login");
        patterns.add("/districts/**");
        patterns.add("/products/**");
        patterns.add("/index.html");    //无论是否登录都能访问主页

//        完成拦截器的注册
        registry.addInterceptor(interceptor).addPathPatterns("/**").excludePathPatterns(patterns);
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/upload/**").addResourceLocations("file:///:D:/编程/springboot/store/src/main/resources/static/upload/");
//    }
}
