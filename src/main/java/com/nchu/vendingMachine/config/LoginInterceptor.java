package com.nchu.vendingMachine.config;

import com.nchu.vendingMachine.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 162015班 第13组
 * 智能售货机后台管理系统——登录拦截器
 *
 * @Author: 16201533朱天保
 * @Date: 2019/6/25 10:30
 * @Version 1.0
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        String requestURI = request.getRequestURI();
        String[] split = requestURI.split("/");
        if (user!=null&&split.length > 2 && split[2].equals(user.getRole())){//获取用户的角色，只有角色对应才返回true
            return true;
        }
        response.sendRedirect("/back");//进入登录界面
        return false;
    }
}
