package com.aptech.foodmarket.food_market.builder;

import com.aptech.foodmarket.food_market.vo.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

public final class ItemVOBuilder {
    private Integer id;
    private String name;
    private Float price;
    private String avatar;
    private Boolean status;
    private Integer quantity;
    private String description;
    private Date createdAt;
    private Date editedAt;
    private Boolean active;
    private UnitVO unit;
    private SupplierVO supplier;

    private List<PromotionItemVO> promotions;
    private List<ImageItemVO> imageItems;
//    private List<OrderItem> orderItems;
    private Set<CategoryVO> categories;

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

    public ItemVOBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public ItemVOBuilder withCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public ItemVOBuilder withEditedAt(Date editedAt) {
        this.editedAt = editedAt;
        return this;
    }

    public ItemVOBuilder withActive(Boolean active) {
        this.active = active;
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

    public ItemVOBuilder withPromotions(List<PromotionItemVO> promotions) {
        this.promotions = promotions;
        return this;
    }

    public ItemVOBuilder withImageItems(List<ImageItemVO> imageItems) {
        this.imageItems = imageItems;
        return this;
    }
//
//    public ItemVOBuilder withOrderItems(List<OrderItem> orderItems) {
//        this.orderItems = orderItems;
//        return this;
//    }

    public ItemVOBuilder withCategory(Set<CategoryVO> categories) {
        this.categories = categories;
        return this;
    }

    public ItemVO build() {
        ItemVO itemVO = new ItemVO();
        itemVO.setId(id);
        itemVO.setName(name);
        itemVO.setPrice(price);
        itemVO.setAvatar(avatar);
        itemVO.setStatus(status);
        itemVO.setQuantity(quantity);

        itemVO.setDescription(description);
        itemVO.setCreatedAt(createdAt);
        itemVO.setEditedAt(editedAt);
        itemVO.setActive(active);
        itemVO.setUnit(unit);
        itemVO.setSupplier(supplier);
        itemVO.setUnit(unit);
        itemVO.setSupplier(supplier);
        itemVO.setPromotions(promotions);
        itemVO.setImageItems(imageItems);
//        itemVO.setOrderItems(orderItems);
        itemVO.setCategory(categories);
        return itemVO;
    }
}
