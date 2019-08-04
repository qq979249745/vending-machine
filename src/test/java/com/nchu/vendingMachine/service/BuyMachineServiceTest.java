package com.nchu.vendingMachine.service;

import com.nchu.vendingMachine.entity.BuyMachine;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * 162015班 第13组
 * 智能售货机后台管理系统——测试业务层
 *
 * @Author: 16201533朱天保
 * @Date: 2019/6/30 13:15
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BuyMachineServiceTest {

    @Autowired
    private BuyMachineService buyMachineService;

    @Test
    public void getAllBuyMachineByOnline() {
        List<BuyMachine> buyMachines = buyMachineService.getAllBuyMachineByOnline();
        Assertions.assertThat(buyMachines.size()).isEqualTo(Integer.valueOf(6));
    }
}