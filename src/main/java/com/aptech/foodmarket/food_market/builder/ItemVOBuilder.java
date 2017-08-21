package com.aptech.foodmarket.food_market.builder;

import com.aptech.foodmarket.food_market.model.Supplier;
import com.aptech.foodmarket.food_market.model.Unit;
import com.aptech.foodmarket.food_market.vo.ItemVO;

import java.util.Date;
import java.util.List;

public final class ItemVOBuilder {
    private Integer id;
    private String name;
    private Float price;
    private String avatar;
    private Boolean status;
    private Integer quantity;
    private Date createdAt;
    private Date editedAt;
    private Boolean active;
    private Unit unit;
    private Supplier supplier;
    private List<Integer> promotionId;
    private List<Integer> imageItemId;
    private List<Integer> orderItemsId;
    private List<Integer> categoriesId;

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

    public ItemVOBuilder withUnit(Unit unit) {
        this.unit = unit;
        return this;
    }

    public ItemVOBuilder withSupplier(Supplier supplier) {
        this.supplier = supplier;
        return this;
    }

    public ItemVOBuilder withPromotionId(List<Integer> promotionId) {
        this.promotionId = promotionId;
        return this;
    }

    public ItemVOBuilder withImageItemId(List<Integer> imageItemId) {
        this.imageItemId = imageItemId;
        return this;
    }

    public ItemVOBuilder withOrderItemsId(List<Integer> orderItemsId) {
        this.orderItemsId = orderItemsId;
        return this;
    }

    public ItemVOBuilder withCategoriesId(List<Integer> categoriesId) {
        this.categoriesId = categoriesId;
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
        itemVO.setCreatedAt(createdAt);
        itemVO.setEditedAt(editedAt);
        itemVO.setActive(active);
        itemVO.setUnit(unit);
        itemVO.setSupplier(supplier);
        itemVO.setPromotionId(promotionId);
        itemVO.setImageItemId(imageItemId);
        itemVO.setOrderItemsId(orderItemsId);
        itemVO.setCategoriesId(categoriesId);
        return itemVO;
    }
}
