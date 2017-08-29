package com.aptech.foodmarket.food_market.builder;

import com.aptech.foodmarket.food_market.model.Item;
import com.aptech.foodmarket.food_market.model.Promotion;
import com.aptech.foodmarket.food_market.vo.PromotionItemVO;

import java.util.Date;

public final class PromotionItemVOBuilder {
    private Integer id;
    private Integer percent;
    private Date createdAt;
    private Date editedAt;
    private Boolean active;
    private Promotion promotion;
    private Item item;

    private PromotionItemVOBuilder() {
    }

    public static PromotionItemVOBuilder aPromotionItemVO() {
        return new PromotionItemVOBuilder();
    }

    public PromotionItemVOBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public PromotionItemVOBuilder withPercent(Integer percent) {
        this.percent = percent;
        return this;
    }

    public PromotionItemVOBuilder withCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public PromotionItemVOBuilder withEditedAt(Date editedAt) {
        this.editedAt = editedAt;
        return this;
    }

    public PromotionItemVOBuilder withActive(Boolean active) {
        this.active = active;
        return this;
    }

    public PromotionItemVOBuilder withPromotion(Promotion promotion) {
        this.promotion = promotion;
        return this;
    }

    public PromotionItemVOBuilder withItem(Item item) {
        this.item = item;
        return this;
    }

    public PromotionItemVO build() {
        PromotionItemVO promotionItemVO = new PromotionItemVO();
        promotionItemVO.setId(id);
        promotionItemVO.setPercent(percent);
        promotionItemVO.setCreatedAt(createdAt);
        promotionItemVO.setEditedAt(editedAt);
        promotionItemVO.setActive(active);
        promotionItemVO.setPromotion(promotion);
        promotionItemVO.setItem(item);
        return promotionItemVO;
    }
}
