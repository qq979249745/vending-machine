package com.nchu.vendingMachine.service;

import com.nchu.vendingMachine.entity.BuyMachine;

import java.util.List;

/**
 * 162015班 第13组
 * 智能售货机后台管理系统——购买售货机模块
 *
 * @Author: 16201533朱天保
 * @Date: 2019/6/26 18:22
 * @Version 1.0
 */
public interface BuyMachineService {
    boolean insertBuyMachine(BuyMachine buyMachine);
    List<BuyMachine> getAllBuyMachine();
    boolean updateState(BuyMachine buyMachine);
    BuyMachine getBuyMachineById(Integer id);
    List<BuyMachine> getAllBuyMachineByOnline();
}
