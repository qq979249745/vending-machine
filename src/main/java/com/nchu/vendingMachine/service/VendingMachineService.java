package com.nchu.vendingMachine.service;

import com.nchu.vendingMachine.entity.VendingMachine;

import java.util.List;

/**
 * 162015班 第13组
 * 智能售货机后台管理系统——售货机模块
 * @Author: 16201533朱天保
 * @Date: 2019/6/12 9:24
 * @Version 1.0
 */
public interface VendingMachineService {
    VendingMachine getVendingMachineById(Integer id);
    List<VendingMachine> getAllVendingMachine();
    boolean deleteVendingMachineById(Integer id);
    boolean updateVendingMachineById(VendingMachine order);
}
