package com.nchu.vendingMachine.entity;


public class SaleProduct {
    private Integer id;

    private Integer productId;

    private Integer saleNum;

    private Integer buyMachineId;

    private BuyMachine buyMachine;

    private Product product;

    public BuyMachine getBuyMachine() {
        return buyMachine;
    }

    public void setBuyMachine(BuyMachine buyMachine) {
        this.buyMachine = buyMachine;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(Integer saleNum) {
        this.saleNum = saleNum;
    }

    public Integer getBuyMachineId() {
        return buyMachineId;
    }

    public void setBuyMachineId(Integer buyMachineId) {
        this.buyMachineId = buyMachineId;
    }
}