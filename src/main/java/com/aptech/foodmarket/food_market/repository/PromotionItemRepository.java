package com.aptech.foodmarket.food_market.repository;

import com.aptech.foodmarket.food_market.model.Category;
import com.aptech.foodmarket.food_market.model.PromotionItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PromotionItemRepository extends JpaRepository<PromotionItem,Integer>{
    PromotionItem findByPromotionIdAndItemId(Integer promotionId, Integer itemId);
    List<PromotionItem> findByPromotionId(Integer promotionId);
}
