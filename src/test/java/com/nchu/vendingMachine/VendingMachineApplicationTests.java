package com.nchu.vendingMachine;

import com.nchu.vendingMachine.dao.UserMapper;
import com.nchu.vendingMachine.entity.User;
import com.nchu.vendingMachine.util.EncryptBasedDes;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VendingMachineApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void contextLoads() {
        /*User user = new User();
        user.setAccount("222222");
        user.setPassword(EncryptBasedDes.encryptBasedDes("222222"));
        user.setRole("operation");
        user.setEmail("222@qq.com");
        user.setAddress("南昌航空大学20栋自动售货机运维人员集合地");
        int i = userMapper.insertSelective(user);
        System.out.println(i);
        Assert.assertEquals(i,1);*/
    }

}
