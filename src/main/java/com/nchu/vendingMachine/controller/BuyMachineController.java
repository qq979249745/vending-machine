package com.nchu.vendingMachine.controller;

import com.nchu.vendingMachine.entity.BuyMachine;
import com.nchu.vendingMachine.entity.RestResponse;
import com.nchu.vendingMachine.entity.VendingMachine;
import com.nchu.vendingMachine.service.BuyMachineService;
import com.nchu.vendingMachine.service.VendingMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 162015班 第13组
 * 智能售货机后台管理系统——购买售货机
 *
 * @Author: 16201520 欧阳超
 * @Date: 2019/6/26 17:16
 * @Version 1.0
 */
@Controller
public class BuyMachineController {

    @Autowired
    private BuyMachineService buyMachineService;

    @Autowired
    private VendingMachineService vendingMachineService;

    @RequestMapping("/back/business/buyMachine")
    public String buyMachine(Model model){
        List<VendingMachine> vendingMachines = vendingMachineService.getAllVendingMachine();
        model.addAttribute(vendingMachines);
        return "back/business/buyMachine";
    }
    @RequestMapping("/back/business/getVendingMachine/{id}")
    public String getVendingMachine(@PathVariable(value = "id") Integer id,Model model){
        VendingMachine vendingMachine = vendingMachineService.getVendingMachineById(id);
        model.addAttribute(vendingMachine);
        return "back/business/machineDetail";
    }
    @PostMapping("/back/business/buyMachine")
    @ResponseBody
    public RestResponse buyMachine(@Valid BuyMachine buyMachine, BindingResult result){
        if (result.hasErrors()){
            return RestResponse.fail().add("data",result.getAllErrors().get(0).getDefaultMessage());
        }else {
            buyMachine.setState("待运输");
            return buyMachineService.insertBuyMachine(buyMachine)?RestResponse.success().add("data","/back/business/showMachine")
                    :RestResponse.fail().add("data","购买失败");
        }
    }

    @GetMapping("/back/business/showMachine")
    public String showManager(Model model){
        List<BuyMachine> buyMachines = buyMachineService.getAllBuyMachine();
        model.addAttribute(buyMachines);
        return "back/business/showMachine";
    }

    @RequestMapping("/back/factory/saleManager")
    public String saleManager(Model model){
        List<BuyMachine> buyMachines = buyMachineService.getAllBuyMachine();
        model.addAttribute(buyMachines);
        return "back/factory/saleManager";
    }
    @PostMapping("/back/factory/saleManager")
    @ResponseBody
    public RestResponse saleManager(Integer id,String state){
        BuyMachine buyMachine = new BuyMachine();
        buyMachine.setId(id);
        buyMachine.setState(state);
        return buyMachineService.updateState(buyMachine)?RestResponse.success().add("data","/back/factory/saleManager")
                :RestResponse.fail().add("data","修改失败");
    }
    @PostMapping("/back/operation/updateMachine")
    @ResponseBody
    public RestResponse updateMachine(Integer id,String state){
        BuyMachine buyMachine = new BuyMachine();
        buyMachine.setId(id);
        buyMachine.setState(state);
        return buyMachineService.updateState(buyMachine)?RestResponse.success().add("data","/back/operation")
                :RestResponse.fail().add("data","修改失败");
    }
}
