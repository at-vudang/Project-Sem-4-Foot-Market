package com.aptech.foodmarket.food_market.builder;

import com.aptech.foodmarket.food_market.model.*;
import com.aptech.foodmarket.food_market.vo.ItemVO;
import com.aptech.foodmarket.food_market.vo.SupplierVO;
import com.aptech.foodmarket.food_market.vo.UnitVO;

import java.util.Date;
import java.util.List;

public final class ItemVOBuilder {

    private Integer id;
    private String name;
    private Float price;
    private String avatar;
    private Boolean status;
    private Integer quantity;
    private UnitVO unit;
    private SupplierVO supplier;
//    private List<PromotionItem> promotions;
//    private List<ImageItem> imageItems;
//    private List<OrderItem> orderItems;
//    private List<Category> category;

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

    public ItemVOBuilder withAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public ItemVOBuilder withStatus(Boolean status) {
        this.status = status;
        return this;
    }

    public ItemVOBuilder withQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public ItemVOBuilder withUnit(UnitVO unit) {
        this.unit = unit;
        return this;
    }

    public ItemVOBuilder withSupplier(SupplierVO supplier) {
        this.supplier = supplier;
        return this;
    }

//    public ItemVOBuilder withPromotions(List<PromotionItem> promotions) {
//        this.promotions = promotions;
//        return this;
//    }
//
//    public ItemVOBuilder withImageItems(List<ImageItem> imageItems) {
//        this.imageItems = imageItems;
//        return this;
//    }
//
//    public ItemVOBuilder withOrderItems(List<OrderItem> orderItems) {
//        this.orderItems = orderItems;
//        return this;
//    }
//
//    public ItemVOBuilder withCategory(List<Category> category) {
//        this.category = category;
//        return this;
//    }

    public ItemVO build() {
        ItemVO itemVO = new ItemVO();
        itemVO.setId(id);
        itemVO.setName(name);
        itemVO.setPrice(price);
        itemVO.setAvatar(avatar);
        itemVO.setStatus(status);
        itemVO.setQuantity(quantity);
//        itemVO.setUnit(unit);
//        itemVO.setSupplier(supplier);
        //itemVO.setPromotions(promotions);
//        itemVO.setImageItems(imageItems);
//        itemVO.setOrderItems(orderItems);
      //  itemVO.setCategory(category);
        return itemVO;
    }
}
