package com.nchu.vendingMachine.controller;

import com.nchu.vendingMachine.entity.RestResponse;
import com.nchu.vendingMachine.entity.SaleProduct;
import com.nchu.vendingMachine.service.BuyMachineService;
import com.nchu.vendingMachine.service.ProductService;
import com.nchu.vendingMachine.service.SaleProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * 162015班 第13组
 * 智能售货机后台管理系统——补货
 *
 * @Author: 16201510 高中巍
 * @Date: 2019/6/27 19:50
 * @Version 1.0
 */
@Controller
@RequestMapping("/back")
public class SaleProductController {
    @Autowired
    private BuyMachineService buyMachineService;
    @Autowired
    private SaleProductService saleProductService;
    @Autowired
    private ProductService productService;

    @GetMapping("/operation/saleProduct/{vmId}")
    public String saleProduct(@PathVariable(value = "vmId")Integer vmId,Model model){
        model.addAttribute(buyMachineService.getBuyMachineById(vmId));
        model.addAttribute(saleProductService.getAllSaleProduct(vmId));
        return "back/operation/saleProduct";
    }
    @GetMapping("/operation/addProduct/{vmId}")
    public String addProduct(@PathVariable(value = "vmId")Integer vmId, Model model){
        model.addAttribute(productService.getAllProduct());
        model.addAttribute(vmId);
        return "back/operation/addProduct";
    }
    @PostMapping("/operation/addProduct/{buyMachineId}")
    @ResponseBody
    public RestResponse addProduct(@PathVariable(value = "buyMachineId")Integer buyMachineId, SaleProduct saleProduct){
        if (saleProduct.getProduct().getInventory()<saleProduct.getSaleNum()){
            return RestResponse.fail().add("data","补货数量大于库存");
        }else if (saleProduct.getSaleNum()<1){
            return RestResponse.fail().add("data","补货数量不能小于零");
        }
        return saleProductService.insertSaleProduct(saleProduct)?
                RestResponse.success().add("data","/back/operation/saleProduct/"+buyMachineId):RestResponse.fail().add("data","补货失败");
    }
    @PostMapping("/operation/addSaleNum")
    @ResponseBody
    public RestResponse addSaleNum(Integer id,Integer saleNum){
        SaleProduct saleProduct = saleProductService.getSaleProductById(id);
        if (saleProduct.getProduct().getInventory()<saleNum){
            return RestResponse.fail().add("data","补货数量大于库存");
        }else if (saleNum<1){
            return RestResponse.fail().add("data","补货数量不能小于零");
        }
        saleProduct.setSaleNum(saleNum+saleProduct.getSaleNum());
        saleProduct.getProduct().setInventory(saleProduct.getProduct().getInventory()-saleNum);
        return saleProductService.addSaleNum(saleProduct)?
                RestResponse.success().add("data","/back/operation/saleProduct/"+saleProduct.getBuyMachineId()):RestResponse.fail().add("data","补货失败");
    }
}
