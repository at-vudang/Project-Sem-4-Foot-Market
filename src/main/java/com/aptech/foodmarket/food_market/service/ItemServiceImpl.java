package com.aptech.foodmarket.food_market.service;

import com.aptech.foodmarket.food_market.builder.ItemVOBuilder;
import com.aptech.foodmarket.food_market.builder.UnitVOBuilder;
import com.aptech.foodmarket.food_market.model.Item;
import com.aptech.foodmarket.food_market.repository.ItemRepository;
import com.aptech.foodmarket.food_market.vo.ItemVO;
import com.aptech.foodmarket.food_market.vo.UnitVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private ItemRepository itemRepository;

    public List<ItemVO> defaultJson(List<Item> items) {

        List<ItemVO> itemVOS = new ArrayList<>();
        items.stream().forEach(item -> {
            UnitVO unitVO = new UnitVO();
            unitVO = UnitVOBuilder.anUnitVO().withId(item.getUnit().getId()).build();
            itemVOS.add(ItemVOBuilder.anItemVO().withId(item.getId())
                    .withName(item.getName())
                    .withPrice(item.getPrice())
                    .withAvatar(item.getAvatar())
                    .withQuantity(item.getQuantity())
                    .withCreatedAt(item.getCreatedAt())
                    .withEditedAt(item.getEditedAt())
//                    .withSupplier(item.getSupplier())
                    .withUnit(unitVO)
                    .withPromotions(item.getPromotionItems())
//                    .withImageItems(item.getImageItems())
//                    .withOrderItems(item.getOrderItems())
//                    .withCategory(item.getCategories())
                    .build());
        });
        return itemVOS;

    }

    @Override
    public List<ItemVO> getAll() {

        List<Item> items = itemRepository.findAll();
        return this.defaultJson(items);
    }

    @Override
    public List<ItemVO> getItemByName(String name) {
        List<Item> items = itemRepository.findByName(name);
        return this.defaultJson(items);
    }

    @Override
    public List<ItemVO> getItemByCategory(int cateId) {
        List<Item> items = itemRepository.findItemsByCategories(cateId);
        return this.defaultJson(items);
    }

    @Override
    public List<ItemVO> getItemByCategoryOrName(String key) {

        return null;
    }

    @Override
    public List<ItemVO> getItemBestSeller(int quantity) {
        return null;
    }

    @Override
    public List<ItemVO> getItemPromotion(int quantity) {
        return null;
    }

    @Override
    public List<ItemVO> getItemNew(int quantity) {
        return this.defaultJson(itemRepository.findAllByOrderByIdDesc()).subList(0,quantity);
    }
}
