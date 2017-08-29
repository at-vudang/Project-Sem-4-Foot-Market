package com.aptech.foodmarket.food_market.service;


import com.aptech.foodmarket.food_market.vo.CategoryVO;

import java.util.List;

public interface CategoryService {
    public List<CategoryVO> getCategoriesByLevel(Integer level);
    public List<CategoryVO> getCategoriesByParent(Integer parentID);
}
