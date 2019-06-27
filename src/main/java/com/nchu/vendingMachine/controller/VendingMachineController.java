package com.nchu.vendingMachine.controller;

import com.nchu.vendingMachine.entity.RestResponse;
import com.nchu.vendingMachine.entity.VendingMachine;
import com.nchu.vendingMachine.service.VendingMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

/**
 * 162015班 第13组
 * 智能售货机后台管理系统——售货机模块
 *
 * @Author: 16201533朱天保
 * @Date: 2019/6/25 11:03
 * @Version 1.0
 */
@Controller
@RequestMapping("/back")
public class VendingMachineController {

    @Autowired
    private VendingMachineService vendingMachineService;

    @GetMapping("/factory/addVendingMachine")
    public String addVendingMachine(){
        return "back/factory/addVendingMachine";
    }

    @PostMapping("/factory/addVendingMachine")
    @ResponseBody
    public RestResponse addVendingMachine(@Valid VendingMachine vendingMachine, BindingResult result,@RequestParam("file") MultipartFile file ){
        if (file.getSize()<=0){
            return RestResponse.fail().add("data","请上传图片");
        }
        if (result.hasErrors()){
            return RestResponse.fail().add("data",result.getFieldError().getDefaultMessage());
        }else {
            return vendingMachineService.addVendingMachine(vendingMachine,file)? RestResponse.success().add("data","/back/factory/editVendingMachine/"+vendingMachine.getId())
                    :RestResponse.fail().add("data","上传失败");
        }
    }

    @GetMapping("/factory/getAllVM")
    public String getAllVM(){
        return "back/factory/getAllVM";
    }
    @GetMapping("/factory/editVendingMachine/{id}")
    public String editVendingMachine(@PathVariable(value = "id")Integer id, Model model){
        VendingMachine vendingMachine = vendingMachineService.getVendingMachineById(id);
        model.addAttribute(vendingMachine);
        return "back/factory/editVendingMachine";
    }
    @PostMapping("/factory/editVendingMachine")
    @ResponseBody
    public RestResponse editVendingMachine(@Valid VendingMachine vendingMachine, BindingResult result,@RequestParam("file") MultipartFile file ){
        if (file.getSize()<=0){
            return RestResponse.fail().add("data","请上传图片");
        }
        if (result.hasErrors()){
            return RestResponse.fail().add("data",result.getFieldError().getDefaultMessage());
        }else {
            return vendingMachineService.updateVendingMachineById(vendingMachine,file)? RestResponse.success().add("data","/back/factory/editVendingMachine/"+vendingMachine.getId())
                    :RestResponse.fail().add("data","上传失败");
        }
    }
}
