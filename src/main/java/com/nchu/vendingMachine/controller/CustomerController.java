package com.nchu.vendingMachine.controller;


import com.nchu.vendingMachine.entity.Order;
import com.nchu.vendingMachine.entity.SaleProduct;
import com.nchu.vendingMachine.service.BuyMachineService;
import com.nchu.vendingMachine.service.OrderService;
import com.nchu.vendingMachine.service.SaleProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 162015班 第13组
 * 智能售货机后台管理系统——顾客购买产品模块
 *
 * @Author: 16201533朱天保
 * @Date: 2019/6/27 23:13
 * @Version 1.0
 */
@Controller
public class CustomerController {


    @Autowired
    private BuyMachineService buyMachineService;

    @Autowired
    private SaleProductService saleProductService;

    @Autowired
    private OrderService orderService;


    @GetMapping("/")
    public String index(Model model){
        model.addAttribute(buyMachineService.getAllBuyMachineByOnline());
        return "index";
    }

    @GetMapping("/getSaleProduct/{vmId}")
    public String getSaleProduct(@PathVariable(name = "vmId")Integer vmId, Model model){
        model.addAttribute(saleProductService.getAllSaleProduct(vmId));
        return "getSaleProduct";
    }

    @GetMapping("/buyProduct/{saleProductId}")
    public String buyProduct(@PathVariable(name = "saleProductId")Integer saleProductId, Model model) {
        SaleProduct saleProduct = saleProductService.getSaleProductById(saleProductId);
        Order order = new Order();
        order.setSaleProductId(saleProduct.getId());
        Date date = new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate=sdf.format(new Date());
        int mod=saleProduct.getBuyMachineId()%100;
        newDate=newDate+((mod>9)?mod:"0"+mod);
        order.setOrderTime(date);
        order.setPrice(saleProduct.getProduct().getPrice());
        order.setStatus("未付款");
        order.setPayNo(newDate);
        orderService.insertOrder(order);
        Order orderByPayNo = orderService.getOrderByPayNo(newDate);

        orderByPayNo.setStatus("已支付");

        orderService.updateOrder(orderByPayNo);
        model.addAttribute(orderByPayNo);
        return "order";
    }

}
