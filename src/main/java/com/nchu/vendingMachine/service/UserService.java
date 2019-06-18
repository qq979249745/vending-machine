package com.nchu.vendingMachine.service;

import com.nchu.vendingMachine.entity.User;

import java.util.List;

/**
 * 162015班 第13组
 * 智能售货机后台管理系统——用户模块
 * @Author: 16201533朱天保
 * @Date: 2019/6/12 9:27
 * @Version 1.0
 */
public interface UserService {
    User getUserById(Integer id);
    List<User> getAllUser();
    boolean deleteUserById(Integer id);
    boolean updateUserById(User order);
}
