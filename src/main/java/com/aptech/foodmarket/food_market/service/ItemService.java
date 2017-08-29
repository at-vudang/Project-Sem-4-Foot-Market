package com.aptech.foodmarket.food_market.service;

import com.aptech.foodmarket.food_market.vo.ItemVO;

import java.util.List;

public interface ItemService {

    public List<ItemVO> getByCategoryName(String name);
}
