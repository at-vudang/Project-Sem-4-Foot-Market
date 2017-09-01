package com.aptech.foodmarket.food_market.repository;

import com.aptech.foodmarket.food_market.model.Category;
import com.aptech.foodmarket.food_market.model.PromotionItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionItemRepository extends JpaRepository<PromotionItem,Integer>{
    PromotionItem findByPromotionAndItem(Integer promotionId, Integer itemId);
    PromotionItem findByPromotionIdAndItemId(Integer promotionId, Integer itemId);
    PromotionItem findFirstByPromotionAndItem(Integer promotionId, Integer itemId);
}
