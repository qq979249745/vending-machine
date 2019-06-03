package com.nchu.vendingMachine.service.impl;

import com.nchu.vendingMachine.dao.OrderMapper;
import com.nchu.vendingMachine.entity.Order;
import com.nchu.vendingMachine.entity.OrderExample;
import com.nchu.vendingMachine.service.OrderService;
import com.nchu.vendingMachine.service.SaleProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 162015班 第13组
 * 智能售货机后台管理系统——订单模块
 *
 * @Author: 16201533朱天保
 * @Date: 2019/6/28 6:39
 * @Version 1.0
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private SaleProductService saleProductService;

    @Override
    public Order getOrderByPayNo(String payNo) {
        OrderExample orderExample = new OrderExample();
        orderExample.createCriteria().andPayNoEqualTo(payNo);
        List<Order> orders = orderMapper.selectByExample(orderExample);
        orders.get(0).setSaleProduct(saleProductService.getSaleProductById(orders.get(0).getSaleProductId()));
        return orders.get(0);
    }

    @Override
    public List<Order> getAllOrder() {
        return null;
    }

    @Override
    public boolean insertOrder(Order order) {
        return orderMapper.insertSelective(order)>0;
    }

    @Override
    public boolean updateOrder(Order order) {
        return orderMapper.updateByPrimaryKey(order)>0;
    }
}
