package com.nchu.vendingMachine.service;

import com.nchu.vendingMachine.entity.Product;

import java.util.List;

/**
 * 162015班 第13组
 * 智能售货机后台管理系统——商品模块
 * @Author: 16201533朱天保
 * @Date: 2019/6/12 9:26
 * @Version 1.0
 */
public interface ProductService {
    Product getProductById(Integer id);
    List<Product> getAllProduct();
    boolean deleteProductById(Integer id);
    boolean updateProductById(Product order);
}
