package com.aptech.foodmarket.food_market.service.ImplService;

import com.aptech.foodmarket.food_market.builder.CategoryVOBuilder;
import com.aptech.foodmarket.food_market.builder.PromotionItemVOBuilder;
import com.aptech.foodmarket.food_market.model.PromotionItem;
import com.aptech.foodmarket.food_market.repository.PromotionItemRepository;
import com.aptech.foodmarket.food_market.service.PromotionItemService;
import com.aptech.foodmarket.food_market.vo.CategoryVO;
import com.aptech.foodmarket.food_market.vo.PromotionItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromotionItemServiceImpl implements PromotionItemService {
    @Autowired
    PromotionItemRepository promotionItemRepository;
    @Override
    public PromotionItemVO getPromotionItemByPromotionAndItem(Integer promotionId, Integer itemId) {
        PromotionItem promotionItem = promotionItemRepository.
                findByPromotionIdAndItemId(promotionId, itemId);
        PromotionItemVO promotionItemVO = PromotionItemVOBuilder.aPromotionItemVO()
                .withId(promotionItem.getId())
                .withPercent(promotionItem.getPercent())
                .build();
        return promotionItemVO;
    }
}
