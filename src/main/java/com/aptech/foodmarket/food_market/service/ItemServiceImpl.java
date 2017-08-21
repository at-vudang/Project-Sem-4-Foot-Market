package com.aptech.foodmarket.food_market.service;

import com.aptech.foodmarket.food_market.builder.ItemVOBuilder;
import com.aptech.foodmarket.food_market.model.Item;
import com.aptech.foodmarket.food_market.repository.ItemRepository;
import com.aptech.foodmarket.food_market.vo.ItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService{
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private ItemRepository itemRepository;

    public List<ItemVO> getAll() {

        List<ItemVO> itemVOS = new ArrayList<>();
        List<Item> items = itemRepository.findAll();
            items.stream().forEach(item -> {
                itemVOS.add(ItemVOBuilder.anItemVO().withId(item.getId())
                        .withActive(item.getActive())
                        .withAvatar(item.getAvatar())
                        .withCategoriesId(item.getCategories().stream().map(category -> category.getId()).collect(Collectors.toList()))
                        .withCreatedAt(item.getCreatedAt())
                        .build());
            });
        return itemVOS;
    }

    public List<Item> getItemByName(String name) {
        return itemRepository.findByName(name);
    }
}
