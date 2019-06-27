package com.nchu.vendingMachine.controller;

import com.nchu.vendingMachine.entity.BuyMachine;
import com.nchu.vendingMachine.entity.RestResponse;
import com.nchu.vendingMachine.entity.User;
import com.nchu.vendingMachine.entity.VendingMachine;
import com.nchu.vendingMachine.service.BuyMachineService;
import com.nchu.vendingMachine.service.LoginService;
import com.nchu.vendingMachine.service.VendingMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * 162015班 第13组
 * 智能售货机后台管理系统——后台模块
 *
 * @Author: 16201533朱天保
 * @Date: 2019/6/17 10:08
 * @Version 1.0
 */
@Controller
@RequestMapping("/back")
public class BackController {
    @Autowired
    private VendingMachineService vendingMachineService;

    @Autowired
    private BuyMachineService buyMachineService;

    @Autowired
    private LoginService loginService;

    @RequestMapping("")
    public String index(){
        return "back/login";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "back/login";
    }

    @PostMapping("/login")
    @ResponseBody
    public RestResponse login(@Valid User user, BindingResult result, HttpSession session){
        if (result.hasErrors()){
            return RestResponse.fail().add("data",result.getAllErrors().get(0).getDefaultMessage());
        }else {
            List<User> users = loginService.login(user);
            if (users.size()!=1){
                return RestResponse.fail().add("data","账号或密码错误");
            }else {
                session.setAttribute("user",users.get(0));
                return RestResponse.success().add("data","/back/"+users.get(0).getRole());
            }
        }
    }
    @RequestMapping("/business")
    public String business(){
        return "back/business/index";
    }
    @RequestMapping("/factory")//售货机厂商
    public String factory(Model model){
        List<VendingMachine> vendingMachines = vendingMachineService.getAllVendingMachine();
        model.addAttribute(vendingMachines);
        return "back/factory/index";
    }
    @RequestMapping("/operation")//运维人员
    public String operation(Model model){
        List<BuyMachine> buyMachines = buyMachineService.getAllBuyMachine();
        model.addAttribute(buyMachines);
        return "back/operation/index";
    }
}
