package com.aptech.foodmarket.food_market.vo;

import com.aptech.foodmarket.food_market.model.Item;
import com.aptech.foodmarket.food_market.model.Promotion;
import com.aptech.foodmarket.food_market.model.PromotionItem;
import java.util.Date;
import java.util.List;

public class PromotionVO {
    private Integer id;
    private String title;
    private Date fromAt;
    private Date endAt;
    private Boolean active;
    private List<PromotionItemVO> promotionItems;

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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<PromotionItemVO> getPromotionItems() {
        return promotionItems;
    }

    public void setPromotionItems(List<PromotionItemVO> promotionItems) {
        this.promotionItems = promotionItems;
    }
}
