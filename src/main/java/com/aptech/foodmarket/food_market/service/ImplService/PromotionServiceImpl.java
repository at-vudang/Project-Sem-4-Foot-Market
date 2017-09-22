package com.aptech.foodmarket.food_market.service.ImplService;

import com.aptech.foodmarket.food_market.builder.PromotionItemVOBuilder;
import com.aptech.foodmarket.food_market.builder.PromotionVOBuilder;
import com.aptech.foodmarket.food_market.model.Promotion;
import com.aptech.foodmarket.food_market.model.PromotionItem;
import com.aptech.foodmarket.food_market.repository.PromotionRepository;
import com.aptech.foodmarket.food_market.service.PromotionService;
import com.aptech.foodmarket.food_market.vo.PromotionItemVO;
import com.aptech.foodmarket.food_market.vo.PromotionVO;
import org.springframework.beans.factory.annotation.Autowired;

public class PromotionServiceImpl implements PromotionService {
    @Autowired
    PromotionRepository promotionRepository;
    @Override
    public PromotionVO deleteItem(int id) {
        Promotion promotion = promotionRepository.findOne(id);
        PromotionVO promotionVO = this.convertVO(promotion);
        promotionRepository.delete(id);
        return promotionVO;
    }
    public PromotionVO convertVO(Promotion promotion) {
        PromotionVO promotionVO = PromotionVOBuilder.aPromotionVO()
                .withId(promotion.getId())
                .withFromAt(promotion.getFromAt())
                .withEndAt(promotion.getEndAt())
                .withPromotionItems(promotion.getPromotionItems())
                .withOrders(promotion.getOrders())
                .withTitle(promotion.getTitle())
                .build();
        return promotionVO;
    }
}
