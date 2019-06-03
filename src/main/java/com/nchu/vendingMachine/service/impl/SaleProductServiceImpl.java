package com.nchu.vendingMachine.service.impl;

import com.nchu.vendingMachine.dao.SaleProductMapper;
import com.nchu.vendingMachine.entity.SaleProduct;
import com.nchu.vendingMachine.entity.SaleProductExample;
import com.nchu.vendingMachine.service.BuyMachineService;
import com.nchu.vendingMachine.service.ProductService;
import com.nchu.vendingMachine.service.SaleProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 162015班 第13组
 * 智能售货机后台管理系统——补货模块
 *
 * @Author: 16201533朱天保
 * @Date: 2019/6/27 19:54
 * @Version 1.0
 */
@Service
public class SaleProductServiceImpl  implements SaleProductService {
    @Autowired
    private SaleProductMapper saleProductMapper;
    @Autowired
    private BuyMachineService buyMachineService;
    @Autowired
    private ProductService productService;

    @Override
    public List<SaleProduct> getAllSaleProduct(Integer vmId) {
        SaleProductExample saleProductExample = new SaleProductExample();
        saleProductExample.createCriteria().andBuyMachineIdEqualTo(vmId);
        saleProductExample.setOrderByClause(" id desc");
        List<SaleProduct> saleProducts = saleProductMapper.selectByExample(saleProductExample);
        for (SaleProduct saleProduct : saleProducts) {
            saleProduct.setBuyMachine(buyMachineService.getBuyMachineById(saleProduct.getBuyMachineId()));
            saleProduct.setProduct(productService.getProductById(saleProduct.getProductId()));
        }
        return saleProducts;
    }

    @Override
    @Transactional
    public boolean insertSaleProduct(SaleProduct saleProduct) {
        saleProduct.getProduct().setId(saleProduct.getProductId());
        saleProduct.getProduct().setInventory(saleProduct.getProduct().getInventory()-saleProduct.getSaleNum());
        return productService.updateProductById(saleProduct.getProduct())&&saleProductMapper.insertSelective(saleProduct) > 0;
    }

    @Override
    public boolean addSaleNum(SaleProduct saleProduct) {

        return productService.updateProductById(saleProduct.getProduct())&&saleProductMapper.updateByPrimaryKeySelective(saleProduct) > 0;
    }

    @Override
    public SaleProduct getSaleProductById(Integer id) {
        SaleProduct saleProduct = saleProductMapper.selectByPrimaryKey(id);
        saleProduct.setProduct(productService.getProductById(saleProduct.getProductId()));
        saleProduct.setBuyMachine(buyMachineService.getBuyMachineById(saleProduct.getBuyMachineId()));
        return saleProduct;
    }
}
