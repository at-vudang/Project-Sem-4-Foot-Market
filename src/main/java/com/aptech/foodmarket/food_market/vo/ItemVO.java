package com.aptech.foodmarket.food_market.vo;

import com.aptech.foodmarket.food_market.model.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

public class ItemVO {
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
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getEditedAt() {
        return editedAt;
    }

    public void setEditedAt(Date editedAt) {
        this.editedAt = editedAt;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public List<Integer> getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(List<Integer> promotionId) {
        this.promotionId = promotionId;
    }

    public List<Integer> getImageItemId() {
        return imageItemId;
    }

    public void setImageItemId(List<Integer> imageItemId) {
        this.imageItemId = imageItemId;
    }

    public List<Integer> getOrderItemsId() {
        return orderItemsId;
    }

    public void setOrderItemsId(List<Integer> orderItemsId) {
        this.orderItemsId = orderItemsId;
    }

    public List<Integer> getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(List<Integer> categoriesId) {
        this.categoriesId = categoriesId;
    }


}
