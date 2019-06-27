package com.nchu.vendingMachine.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 162015班 第13组
 * 智能售货机后台管理系统——登录拦截器
 *
 * @Author: 16201533朱天保
 * @Date: 2019/6/25 10:25
 * @Version 1.0
 */
@Configuration
public class LoginConfigurer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/back/**").excludePathPatterns("/back","/back/login");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**").addResourceLocations("file:C:/image/");
    }
}
