package com.aptech.foodmarket.food_market.service.ImplService;

import com.aptech.foodmarket.food_market.builder.PromotionItemVOBuilder;
import com.aptech.foodmarket.food_market.model.PromotionItem;
import com.aptech.foodmarket.food_market.repository.PromotionItemRepository;
import com.aptech.foodmarket.food_market.service.PromotionItemService;
import com.aptech.foodmarket.food_market.vo.ItemVO;
import com.aptech.foodmarket.food_market.vo.PromotionItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSException;

import java.util.ArrayList;
import java.util.List;

@Service
public class PromotionItemServiceImpl implements PromotionItemService {
    @Autowired
    PromotionItemRepository promotionItemRepository;
    @Autowired
    ItemServiceImpl itemService;
    @Override
    public PromotionItemVO getPromotionItemByPromotionAndItem(Integer promotionId, Integer itemId) {
        PromotionItem promotionItem = promotionItemRepository.
                findByPromotionIdAndItemId(promotionId, itemId);
        PromotionItemVO promotionItemVO = new PromotionItemVO();
        if (promotionItem != null) {
             promotionItemVO = PromotionItemVOBuilder.aPromotionItemVO()
                    .withId(promotionItem.getId())
                    .withPercent(promotionItem.getPercent())
                    .build();
        }
        return promotionItemVO;
    }

    @Override
    public List<PromotionItemVO> getPromotionItemByPromotion(Integer promotionId) {
        List<PromotionItem> promotionItems = promotionItemRepository.
                findByPromotionId(promotionId);
        List<PromotionItemVO> promotionItemVOS = new ArrayList<>();
        for (PromotionItem promotionItem: promotionItems
             ) {
            promotionItemVOS.add(convertVOWithItem(promotionItem));
        }
        return promotionItemVOS;
    }
    public PromotionItemVO deleteItem(int id) {
        PromotionItem promotionItem = promotionItemRepository.findOne(id);
        PromotionItemVO promotionItemVO = this.convertVO(promotionItem);
        promotionItemRepository.delete(id);
        return promotionItemVO;
    }
    public PromotionItemVO convertVO(PromotionItem promotionItem) {
        PromotionItemVO promotionItemVO = PromotionItemVOBuilder.aPromotionItemVO()
                .withPercent(promotionItem.getPercent())
                .withId(promotionItem.getId()).build();
        return promotionItemVO;
    }

    public PromotionItemVO convertVOWithItem(PromotionItem promotionItem) {
        ItemVO itemVO = itemService.convertVO(promotionItem.getItem());
        PromotionItemVO promotionItemVO = PromotionItemVOBuilder.aPromotionItemVO()
                .withPercent(promotionItem.getPercent())
                .withId(promotionItem.getId())
                .withItem(itemVO)
                .build();
        return promotionItemVO;
    }
    @Override
    public PromotionItemVO create(PromotionItem promotionItem) {
        return convertVO(promotionItemRepository.save(promotionItem));
    }

    @Override
    public PromotionItemVO update(PromotionItem promotionItem) {
        PromotionItem promotionItemUpdate;
        promotionItemUpdate = promotionItemRepository.findOne(promotionItem.getId());
        promotionItemUpdate.setItem(promotionItem.getItem());
        promotionItemUpdate.setPercent(promotionItem.getPercent());
        promotionItemRepository.save(promotionItemUpdate);
        return convertVO(promotionItemRepository.findOne(promotionItemUpdate.getId()));
    }
}
