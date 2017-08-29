package com.aptech.foodmarket.food_market.service;

import com.aptech.foodmarket.food_market.vo.CategoryVO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService{

    public List<CategoryVO> getAllNameCategory();
}
