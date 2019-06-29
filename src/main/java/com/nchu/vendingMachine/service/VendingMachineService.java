package com.nchu.vendingMachine.service;

import com.nchu.vendingMachine.entity.VendingMachine;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 162015班 第13组
 * 智能售货机后台管理系统——售货机
 * @Author: 16201533朱天保
 * @Date: 2019/6/12 9:24
 * @Version 1.0
 */
public interface VendingMachineService {


    boolean addVendingMachine(VendingMachine vendingMachine, MultipartFile file);
    VendingMachine getVendingMachineById(Integer id);
    List<VendingMachine> getAllVendingMachine();
    boolean deleteVendingMachineById(Integer id);
    boolean updateVendingMachineById(VendingMachine vendingMachine, MultipartFile file);
}
