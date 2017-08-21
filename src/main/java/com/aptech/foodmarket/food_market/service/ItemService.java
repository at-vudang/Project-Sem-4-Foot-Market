package com.aptech.foodmarket.food_market.service;

import com.aptech.foodmarket.food_market.model.Item;
import com.aptech.foodmarket.food_market.vo.ItemVO;

import java.util.List;

public interface ItemService {

    public List<Item> getItemByName(String name);

    public List<ItemVO> getAll();
}
