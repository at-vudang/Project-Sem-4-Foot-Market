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
    private UnitVO unit;
    private SupplierVO supplier;
    private List<PromotionItemVO> promotions;
//    private List<ImageItem> imageItems;
//    private List<OrderItem> orderItems;
//    private List<Category> category;

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

    public UnitVO getUnit() {
        return unit;
    }

    public void setUnit(UnitVO unit) {
        this.unit = unit;
    }

    //    public UnitVO getUnit() {
//        return unit;
//    }
//
//    public void setUnit(UnitVO unit) {
//        this.unit = unit;
//    }
//
    public SupplierVO getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierVO supplier) {
        this.supplier = supplier;
    }

    public List<PromotionItemVO> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<PromotionItemVO> promotions) {
        this.promotions = promotions;
    }

//    public List<ImageItem> getImageItems() {
//        return imageItems;
//    }
//
//    public void setImageItems(List<ImageItem> imageItems) {
//        this.imageItems = imageItems;
//    }
//
//    public List<OrderItem> getOrderItems() {
//        return orderItems;
//    }
//
//    public void setOrderItems(List<OrderItem> orderItems) {
//        this.orderItems = orderItems;
//    }

//    public List<Category> getCategory() {
//        return category;
//    }
//
//    public void setCategory(List<Category> category) {
//        this.category = category;
//    }
}
