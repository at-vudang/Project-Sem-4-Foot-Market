package com.aptech.foodmarket.food_market.controller;

import com.aptech.foodmarket.food_market.model.Item;
import com.aptech.foodmarket.food_market.model.User;
import com.aptech.foodmarket.food_market.repository.ItemRepository;
import com.aptech.foodmarket.food_market.service.ItemService;
import com.aptech.foodmarket.food_market.vo.ItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ItemController {
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemService itemService;

    @RequestMapping("/all")
    @ResponseBody
    public List<ItemVO> getAll() {
        return itemService.getAll();
    }

    @RequestMapping("/getItemByName")
    @ResponseBody
    public List<ItemVO> getItemByName(String name) {
        return itemService.getItemByName(name);
    }

    @RequestMapping("/getItemById")
    @ResponseBody
    public Item getItemByName(int id) {
        return itemRepository.findOne(id);
    }

    @RequestMapping("/getItemNew")
    @ResponseBody
    public List<ItemVO> getItemNew(int quantity) {
        return itemService.getItemNew(quantity);
    }

}
