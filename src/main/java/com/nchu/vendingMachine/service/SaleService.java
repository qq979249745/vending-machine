package com.nchu.vendingMachine.service;

import com.nchu.vendingMachine.entity.Sale;

import java.util.List;

/**
 * 162015班 第13组
 * 智能售货机后台管理系统——销售模块
 * @Author: 16201533朱天保
 * @Date: 2019/6/12 9:26
 * @Version 1.0
 */
public interface SaleService {
    Sale getSaleById(Integer id);
    List<Sale> getAllSale();
    boolean deleteSaleById(Integer id);
    boolean updateSaleById(Sale order);
}
