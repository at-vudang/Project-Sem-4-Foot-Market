package com.aptech.foodmarket.food_market.service;

import com.aptech.foodmarket.food_market.vo.ImageItemVO;
import com.aptech.foodmarket.food_market.vo.ItemVO;

import java.util.List;

public interface ImageService {
    List<ImageItemVO> getImageOfItem(int id);
    ImageItemVO deleteItem(int id);

}
