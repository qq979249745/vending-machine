package com.nchu.vendingMachine.entity;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

public class Product {
    private Integer id;
    @Pattern(regexp = ".{2,32}",message = "商品名字长度为2到32个字符")
    private String name;
    @NotNull(message = "填写的价格有误")
    @DecimalMin(value = "0",message = "价格不能小于0")
    private BigDecimal price;
    @NotNull(message = "填写的进货量有误")
    @Min(value = 1,message = "进货量不能小于1")
    private Integer inventory;

    private String imgPath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath == null ? null : imgPath.trim();
    }
}