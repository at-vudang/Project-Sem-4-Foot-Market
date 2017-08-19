package com.aptech.foodmarket.food_market.service;

import com.aptech.foodmarket.food_market.model.Item;
import com.aptech.foodmarket.food_market.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ItemService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getAll() {
        return itemRepository.findAll();
    }
}
