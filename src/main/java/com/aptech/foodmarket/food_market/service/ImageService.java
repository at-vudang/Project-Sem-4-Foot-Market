package com.aptech.foodmarket.food_market.service;

import com.aptech.foodmarket.food_market.vo.ImageItemVO;

import java.util.List;

public interface ImageService {
    List<ImageItemVO> getImageOfItem(int id);
}
