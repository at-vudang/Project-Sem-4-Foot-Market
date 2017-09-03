package com.aptech.foodmarket.food_market.service;


import com.aptech.foodmarket.food_market.model.Category;
import com.aptech.foodmarket.food_market.model.Item;
import com.aptech.foodmarket.food_market.vo.CategoryVO;
import com.aptech.foodmarket.food_market.vo.ItemVO;

import java.util.List;

public interface CategoryService {
    public List<CategoryVO> getCategoriesByLevel(Integer level);
    public List<CategoryVO> getCategoriesByParent(Integer parentID);
    public CategoryVO getCategoryById(Integer id);
    public List<ItemVO> getItems(Integer id);
    public Category create(Category categories);
}
