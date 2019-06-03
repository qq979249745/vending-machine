package com.nchu.vendingMachine.service;

import com.nchu.vendingMachine.entity.SaleProduct;

import java.util.List;

/**
 * 162015班 第13组
 * 智能售货机后台管理系统——补货模块
 *
 * @Author: 16201533朱天保
 * @Date: 2019/6/27 19:53
 * @Version 1.0
 */
public interface SaleProductService {
    List<SaleProduct> getAllSaleProduct(Integer vmId);

    boolean insertSaleProduct(SaleProduct saleProduct);

    boolean addSaleNum(SaleProduct saleProduct);

    SaleProduct getSaleProductById(Integer id);
}
