package com.aptech.foodmarket.food_market.service;

import com.aptech.foodmarket.food_market.vo.ItemVO;
import com.aptech.foodmarket.food_market.vo.UnitVO;

import java.util.List;

public interface UnitService {
    List<UnitVO> getAll();
    UnitVO deleteItem(int id);

}
