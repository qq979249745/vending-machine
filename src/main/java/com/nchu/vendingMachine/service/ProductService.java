package com.nchu.vendingMachine.service;

import com.nchu.vendingMachine.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 162015班 第13组
 * 智能售货机后台管理系统——商品采购
 * @Author: 16201531 周小华
 * @Date: 2019/6/12 9:26
 * @Version 1.0
 */
public interface ProductService {
    boolean addProduct(Product product, MultipartFile file);

    Product getProductById(Integer id);
    List<Product> getAllProduct();
    boolean updateProductById(Product order);

    boolean updateProductById(Product product, MultipartFile file);
}
