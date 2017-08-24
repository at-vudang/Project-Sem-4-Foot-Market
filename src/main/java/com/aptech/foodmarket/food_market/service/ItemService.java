package com.aptech.foodmarket.food_market.service;

import com.aptech.foodmarket.food_market.model.Item;
import com.aptech.foodmarket.food_market.vo.ItemVO;

import java.util.List;

public interface ItemService {

    public List<ItemVO> getItemByName(String name);

    public List<ItemVO> getAll();

    public List<ItemVO> getItemBestSeller( int quantity);

    public List<ItemVO> getItemByCategory( int cateId);

    public List<ItemVO> getItemByCategoryOrName( String key);

    public List<ItemVO> getItemPromotion( int quantity);

    public List<ItemVO> getItemNew( int quantity);

}
