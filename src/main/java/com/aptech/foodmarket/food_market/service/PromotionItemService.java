package com.aptech.foodmarket.food_market.service;

import com.aptech.foodmarket.food_market.model.PromotionItem;
import com.aptech.foodmarket.food_market.vo.PromotionItemVO;

public interface PromotionItemService {
    PromotionItemVO getPromotionItemByPromotionAndItem(Integer promotionId, Integer itemId);
}
