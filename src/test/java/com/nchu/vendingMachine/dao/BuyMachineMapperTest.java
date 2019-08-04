package com.nchu.vendingMachine.dao;

import com.nchu.vendingMachine.entity.BuyMachine;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * 162015班 第13组
 * 智能售货机后台管理系统——单元测试持久层
 *
 * @Author: 16201533朱天保
 * @Date: 2019/6/30 13:05
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BuyMachineMapperTest {
    @Autowired
    private BuyMachineMapper buyMachineMapper;

    @Test
    public void selectByPrimaryKey() {
        BuyMachine buyMachine = buyMachineMapper.selectByPrimaryKey(1);
        Assertions.assertThat(buyMachine.getId()).isEqualTo(Integer.valueOf(1));
        Assertions.assertThat(buyMachine.getVmId()).isEqualTo(Integer.valueOf(3));
        Assertions.assertThat(buyMachine.getLocation()).isEqualTo("南昌航空大学20栋楼下");
        Assertions.assertThat(buyMachine.getState()).isEqualTo("经营中");
    }
}