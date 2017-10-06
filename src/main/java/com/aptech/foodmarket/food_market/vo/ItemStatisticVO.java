package com.aptech.foodmarket.food_market.vo;

import com.aptech.foodmarket.food_market.model.Supplier;

import java.util.Date;

public class ItemStatisticVO {
    private int id;
    private String name;
    private SupplierVO supplier;
    private int quantityOrder;
    private Date beginAt;
    private Date endAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SupplierVO getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierVO supplier) {
        this.supplier = supplier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantityOrder() {
        return quantityOrder;
    }

    public void setQuantityOrder(int quantityOrder) {
        this.quantityOrder = quantityOrder;
    }

    public Date getBeginAt() {
        return beginAt;
    }

    public void setBeginAt(Date beginAt) {
        this.beginAt = beginAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

}
