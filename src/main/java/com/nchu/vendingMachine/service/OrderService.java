package com.nchu.vendingMachine.service;

import com.nchu.vendingMachine.entity.Order;

import java.util.List;

/**
 * 162015班 第13组
 * 智能售货机后台管理系统——订单
 * @Author: 16201533朱天保
 * @Date: 2019/6/12 9:27
 * @Version 1.0
 */
public interface OrderService {
    Order getOrderByPayNo(String payNo);
    List<Order> getAllOrder();
    boolean insertOrder(Order order);
    boolean updateOrder(Order order);
}
