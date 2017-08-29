package com.aptech.foodmarket.food_market.builder;

import com.aptech.foodmarket.food_market.vo.ItemVO;

public final class ItemVOBuilder {
    private Integer id;
    private String name;
    private Float price;

    private ItemVOBuilder() {
    }

    public static ItemVOBuilder anItemVO() {
        return new ItemVOBuilder();
    }

    public ItemVOBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public ItemVOBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ItemVOBuilder withPrice(Float price) {
        this.price = price;
        return this;
    }

    public ItemVO build() {
        ItemVO itemVO = new ItemVO();
        itemVO.setId(id);
        itemVO.setName(name);
        itemVO.setPrice(price);
        return itemVO;
    }
}
