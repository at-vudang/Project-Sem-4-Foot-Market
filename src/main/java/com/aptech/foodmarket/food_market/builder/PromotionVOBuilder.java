package com.aptech.foodmarket.food_market.builder;

import com.aptech.foodmarket.food_market.model.Order;
import com.aptech.foodmarket.food_market.model.PromotionItem;
import com.aptech.foodmarket.food_market.vo.PromotionVO;

import java.util.Date;
import java.util.List;

public final class PromotionVOBuilder {
    private Integer id;
    private String title;
    private Date fromAt;
    private Date endAt;
    private Date createdAt;
    private Date editedAt;
    private Boolean active;
    private List<PromotionItem> promotionItems;
    private List<Order> orders;

    private PromotionVOBuilder() {
    }

    public static PromotionVOBuilder aPromotionVO() {
        return new PromotionVOBuilder();
    }

    public PromotionVOBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public PromotionVOBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public PromotionVOBuilder withFromAt(Date fromAt) {
        this.fromAt = fromAt;
        return this;
    }

    public PromotionVOBuilder withEndAt(Date endAt) {
        this.endAt = endAt;
        return this;
    }

    public PromotionVOBuilder withCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public PromotionVOBuilder withEditedAt(Date editedAt) {
        this.editedAt = editedAt;
        return this;
    }

    public PromotionVOBuilder withActive(Boolean active) {
        this.active = active;
        return this;
    }

    public PromotionVOBuilder withPromotionItems(List<PromotionItem> promotionItems) {
        this.promotionItems = promotionItems;
        return this;
    }

    public PromotionVOBuilder withOrders(List<Order> orders) {
        this.orders = orders;
        return this;
    }

    public PromotionVO build() {
        PromotionVO promotionVO = new PromotionVO();
        promotionVO.setId(id);
        promotionVO.setTitle(title);
        promotionVO.setFromAt(fromAt);
        promotionVO.setEndAt(endAt);
        promotionVO.setCreatedAt(createdAt);
        promotionVO.setEditedAt(editedAt);
        promotionVO.setActive(active);
        promotionVO.setPromotionItems(promotionItems);
        promotionVO.setOrders(orders);
        return promotionVO;
    }
}
