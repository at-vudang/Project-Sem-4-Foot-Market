package com.aptech.foodmarket.food_market.builder;

import com.aptech.foodmarket.food_market.vo.ItemStatisticVO;
import com.aptech.foodmarket.food_market.vo.SupplierVO;

import java.util.Date;

public final class ItemStatisticVOBuilder {
    private int id;
    private String name;
    private SupplierVO supplier;
    private int quantityOrder;
    private Date beginAt;
    private Date endAt;

    private ItemStatisticVOBuilder() {
    }

    public static ItemStatisticVOBuilder anItemStatisticVO() {
        return new ItemStatisticVOBuilder();
    }

    public ItemStatisticVOBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public ItemStatisticVOBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ItemStatisticVOBuilder withSupplier(SupplierVO supplier) {
        this.supplier = supplier;
        return this;
    }

    public ItemStatisticVOBuilder withQuantityOrder(int quantityOrder) {
        this.quantityOrder = quantityOrder;
        return this;
    }

    public ItemStatisticVOBuilder withBeginAt(Date beginAt) {
        this.beginAt = beginAt;
        return this;
    }

    public ItemStatisticVOBuilder withEndAt(Date endAt) {
        this.endAt = endAt;
        return this;
    }

    public ItemStatisticVO build() {
        ItemStatisticVO itemStatisticVO = new ItemStatisticVO();
        itemStatisticVO.setId(id);
        itemStatisticVO.setName(name);
        itemStatisticVO.setSupplier(supplier);
        itemStatisticVO.setQuantityOrder(quantityOrder);
        itemStatisticVO.setBeginAt(beginAt);
        itemStatisticVO.setEndAt(endAt);
        return itemStatisticVO;
    }
}
