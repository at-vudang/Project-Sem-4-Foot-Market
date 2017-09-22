package com.aptech.foodmarket.food_market.service.ImplService;

import com.aptech.foodmarket.food_market.builder.PromotionItemVOBuilder;
import com.aptech.foodmarket.food_market.builder.PromotionVOBuilder;
import com.aptech.foodmarket.food_market.model.Promotion;
import com.aptech.foodmarket.food_market.model.PromotionItem;
import com.aptech.foodmarket.food_market.repository.PromotionItemRepository;
import com.aptech.foodmarket.food_market.repository.PromotionRepository;
import com.aptech.foodmarket.food_market.service.PromotionItemService;
import com.aptech.foodmarket.food_market.service.PromotionService;
import com.aptech.foodmarket.food_market.vo.PromotionItemVO;
import com.aptech.foodmarket.food_market.vo.PromotionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PromotionServiceImpl implements PromotionService {
    @Autowired
    private PromotionItemServiceImpl promotionItemService;
    @Autowired
    private PromotionRepository promotionRepository;

    @Override
    public PromotionVO create(Promotion promotion) {
        promotion = promotionRepository.save(promotion);
        for (PromotionItem promotionItem: promotion.getPromotionItems()
             ) {
            promotionItem.setPromotion(promotion);
            promotionItemService.create(promotionItem);
        }
        promotion = promotionRepository.findOne(promotion.getId());
        return convertVO(promotion);
    }

    @Override
    public PromotionVO update(Promotion promotion) {
        Promotion promotionUpdate;
        promotionUpdate = promotionRepository.findOne(promotion.getId());
        promotionUpdate.setEndAt(promotion.getEndAt());
        promotionUpdate.setFromAt(promotion.getFromAt());
        promotionUpdate.setTitle(promotion.getTitle());
        promotionRepository.save(promotionUpdate);
        return convertVO(promotionRepository.findOne(promotionUpdate.getId()));
    }

    @Override
    public Page<PromotionVO> getAll(int page, int size, String sort) {
        String direction = sort.substring(0,1);
        String keySort = sort.substring(1,sort.length());
        Page<Promotion> items = promotionRepository.findAll(new PageRequest(page, size,
                direction.equals("-") ? Sort.Direction.DESC : Sort.Direction.ASC,
                keySort));
        Page<PromotionVO> promotionVOS = items.map(new Converter<Promotion, PromotionVO>() {
            @Override
            public PromotionVO convert(Promotion entity) {
                return convertVO(entity);
            }
        });
        return promotionVOS;
    }

    @Override
    public Page<PromotionVO> getByDate(Date date, int page, int size, String sort) {
        String direction = sort.substring(0,1);
        String keySort = sort.substring(1,sort.length());
        Page<Promotion> items = promotionRepository
                .findByFromAtGreaterThanEqualOrEndAtIsLessThanEqual(date,
                        date,
                        new PageRequest(page, size,
                                direction.equals("-") ? Sort.Direction.DESC : Sort.Direction.ASC,
                                keySort)
                );
        Page<PromotionVO> promotionVOS = items.map(new Converter<Promotion, PromotionVO>() {
            @Override
            public PromotionVO convert(Promotion entity) {
                return convertVO(entity);
            }
        });
        return promotionVOS;
    }

    public PromotionVO convertVO(Promotion promotion) {
        List<PromotionItemVO> promotionItemVOS = new ArrayList<>();
        if (promotion.getPromotionItems() != null) {
            for (PromotionItem promotionItem: promotion.getPromotionItems()
                    ) {
                PromotionItemVO promotionItemVO = promotionItemService.convertVO(promotionItem);
                promotionItemVOS.add(promotionItemVO);
            }
        }
        PromotionVO promotionVO = PromotionVOBuilder.aPromotionVO()
                .withId(promotion.getId()).withTitle(promotion.getTitle())
                .withActive(promotion.getActive())
                .withEndAt(promotion.getEndAt())
                .withFromAt(promotion.getFromAt())
                .withPromotionItems(promotionItemVOS)
                .build();
        return promotionVO;
    }
}
