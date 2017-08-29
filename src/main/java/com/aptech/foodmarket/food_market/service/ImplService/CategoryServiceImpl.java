package com.aptech.foodmarket.food_market.service.ImplService;

import com.aptech.foodmarket.food_market.builder.CategoryVOBuilder;
import com.aptech.foodmarket.food_market.builder.SupplierVOBuilder;
import com.aptech.foodmarket.food_market.builder.UnitVOBuilder;
import com.aptech.foodmarket.food_market.model.Category;
import com.aptech.foodmarket.food_market.model.Item;
import com.aptech.foodmarket.food_market.model.LevelCategory;
import com.aptech.foodmarket.food_market.repository.CategoryRepository;
import com.aptech.foodmarket.food_market.repository.ItemRepository;
import com.aptech.foodmarket.food_market.service.CategoryService;
import com.aptech.foodmarket.food_market.vo.CategoryVO;
import com.aptech.foodmarket.food_market.vo.CategoryVO;
import com.aptech.foodmarket.food_market.vo.SupplierVO;
import com.aptech.foodmarket.food_market.vo.UnitVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryVO> getCategoriesByLevel(Integer level) {
        LevelCategory levelCategory = new LevelCategory();
        levelCategory.setId(level);
        List<Category> categories = categoryRepository.findByLevelCategory(levelCategory);
        return this.defaultJson(categories);
    }
    public List<CategoryVO> defaultJson(List<Category> categories) {
        List<CategoryVO> categoryVOS = new ArrayList<>();
        categories.stream().forEach(item -> {
            categoryVOS.add(CategoryVOBuilder.aCategoryVO().withId(item.getId())
                    .withName(item.getName())
                    .withDescription(item.getDescription())
                    .withName(item.getName())
                    .build());
        });
        return categoryVOS;
    }

    @Override
    public List<CategoryVO> getCategoriesByParent(Integer parentID) {
        List<Category> categories = categoryRepository.findByParentId(parentID);
        return this.defaultJson(categories);
    }
}
