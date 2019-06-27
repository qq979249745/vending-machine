package com.nchu.vendingMachine.service.impl;

import com.nchu.vendingMachine.dao.BuyMachineMapper;
import com.nchu.vendingMachine.entity.BuyMachine;
import com.nchu.vendingMachine.entity.BuyMachineExample;
import com.nchu.vendingMachine.service.BuyMachineService;
import com.nchu.vendingMachine.service.VendingMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 162015班 第13组
 * 智能售货机后台管理系统——购买售货机模块
 *
 * @Author: 16201533朱天保
 * @Date: 2019/6/26 18:24
 * @Version 1.0
 */
@Service
public class BuyMachineServiceImpl implements BuyMachineService {
    @Autowired
    private BuyMachineMapper buyMachineMapper;
    @Autowired
    private VendingMachineService vendingMachineService;
    @Override
    public boolean insertBuyMachine(BuyMachine buyMachine) {
        return buyMachineMapper.insertSelective(buyMachine) > 0;
    }

    @Override
    public List<BuyMachine> getAllBuyMachine() {
        BuyMachineExample buyMachineExample = new BuyMachineExample();
        buyMachineExample.setOrderByClause(" id desc");
        List<BuyMachine> buyMachines = buyMachineMapper.selectByExample(buyMachineExample);
        for (BuyMachine buyMachine : buyMachines) {
            System.out.println(buyMachine.getId());
            buyMachine.setVendingMachine(vendingMachineService.getVendingMachineById(buyMachine.getVmId()));
        }
        return buyMachines;
    }

    @Override
    public boolean updateState(BuyMachine buyMachine) {
        return buyMachineMapper.updateByPrimaryKeySelective(buyMachine) > 0;
    }
}
