package com.aptech.foodmarket.food_market.controller;

import com.aptech.foodmarket.food_market.model.Item;
import com.aptech.foodmarket.food_market.repository.ItemRepository;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;

    @RequestMapping("/")
    @ResponseBody
    public List<Item> getAll() {
//    @Query("select a FROM Item as a" +
//            "inner join  PromotionItem as b on a.id = b.item_id" +
//            "inner join  Category  c on a.id = c.id")
        return itemRepository.findAll();
    }
}
