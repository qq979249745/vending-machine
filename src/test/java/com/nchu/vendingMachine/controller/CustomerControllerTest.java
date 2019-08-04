package com.nchu.vendingMachine.controller;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * 162015班 第13组
 * 智能售货机后台管理系统——表现层测试
 *
 * @Author: 16201533朱天保
 * @Date: 2019/6/30 12:46
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @Test
    public void index() {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        try {
            mvc.perform(MockMvcRequestBuilders.get("/"))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("南昌航空大学20栋楼下")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}