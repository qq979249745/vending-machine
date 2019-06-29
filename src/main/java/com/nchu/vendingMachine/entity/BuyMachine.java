package com.nchu.vendingMachine.entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class BuyMachine {
    private Integer id;
    @NotNull(message = "购买的售货机不能为空不能为空")
    private Integer vmId;
    @NotEmpty(message = "安装地点不能为空")
    private String location;

    private String state;

    private VendingMachine vendingMachine;

    public VendingMachine getVendingMachine() {
        return vendingMachine;
    }

    public void setVendingMachine(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVmId() {
        return vmId;
    }

    public void setVmId(Integer vmId) {
        this.vmId = vmId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}