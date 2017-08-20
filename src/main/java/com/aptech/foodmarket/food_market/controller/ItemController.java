package com.aptech.foodmarket.food_market.controller;

import com.aptech.foodmarket.food_market.model.Item;
import com.aptech.foodmarket.food_market.model.User;
import com.aptech.foodmarket.food_market.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ItemController {
    @Autowired
    ItemRepository itemRepository ;

    @RequestMapping("/all")
    @ResponseBody
    public List<Item> getAll() {
             return itemRepository.findAll();
    }

    @RequestMapping("/getItemByName")
    @ResponseBody
    public List<Item> getItemByName(String name) {
        System.out.println(name);
        return itemRepository.findByName(name);
    }

    @RequestMapping("/getItemById")
    @ResponseBody
    public Item getItemByName(int id) {
        return itemRepository.findOne(id);
    }
}
