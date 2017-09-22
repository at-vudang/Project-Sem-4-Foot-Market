package com.aptech.foodmarket.food_market.service;

import com.aptech.foodmarket.food_market.model.PromotionItem;
import com.aptech.foodmarket.food_market.vo.PromotionItemVO;

import java.util.List;

public interface PromotionItemService {
    PromotionItemVO getPromotionItemByPromotionAndItem(Integer promotionId, Integer itemId);
    List<PromotionItemVO> getPromotionItemByPromotion(Integer promotionId);
    PromotionItemVO create(PromotionItem promotionItem);
    PromotionItemVO update(PromotionItem promotionItem);
}
