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

@Service
public class ItemServiceImql implements ItemService{

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<ItemVO> getByCategoryName(String name) {
        return null;
    }


//    @Override
//    public List<ItemVO> getByCategoryName(Integer id) {
//        List<Item> items = itemRepository.findAllById(id);
//        List<ItemVO> itemVOS = new ArrayList<>();
//        items.stream().forEach(item -> {
//            itemVOS.add(ItemVOBuilder.anItemVO()
//                    .withId(item.getId())
//                    .withName(item.getName())
//                    .withPrice(item.getPrice())
//                    .build());
//        });
//
//        return null;
//
//    }
}
