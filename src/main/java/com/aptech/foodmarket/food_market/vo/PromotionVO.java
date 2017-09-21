package com.aptech.foodmarket.food_market.vo;

import com.aptech.foodmarket.food_market.model.Order;
import com.aptech.foodmarket.food_market.model.PromotionItem;
import java.util.Date;
import java.util.List;

public class PromotionVO {
    private Integer id;

    private String title;

    private Date fromAt;

    private Date endAt;

    private Date createdAt;

    private Date editedAt;

    private Boolean active;

    private List<PromotionItem> promotionItems;

    private List<Order> orders;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getFromAt() {
        return fromAt;
    }

    public void setFromAt(Date fromAt) {
        this.fromAt = fromAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
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

    public List<PromotionItem> getPromotionItems() {
        return promotionItems;
    }

    public void setPromotionItems(List<PromotionItem> promotionItems) {
        this.promotionItems = promotionItems;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
