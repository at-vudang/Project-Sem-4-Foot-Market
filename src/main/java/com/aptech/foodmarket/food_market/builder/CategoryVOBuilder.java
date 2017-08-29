package com.aptech.foodmarket.food_market.builder;

import com.aptech.foodmarket.food_market.vo.CategoryVO;
import com.aptech.foodmarket.food_market.vo.ItemVO;

import java.util.List;

public final class CategoryVOBuilder {
    private Integer id;
    private String name;
    private String description;
    private Integer levelCategory;
    private List<ItemVO> items;

    private CategoryVOBuilder() {
    }

    public static CategoryVOBuilder aCategoryVO() {
        return new CategoryVOBuilder();
    }

    public CategoryVOBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public CategoryVOBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CategoryVOBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public CategoryVOBuilder withLevelCategory(Integer levelCategory) {
        this.levelCategory = levelCategory;
        return this;
    }

    public CategoryVOBuilder withItems(List<ItemVO> items) {
        this.items = items;
        return this;
    }

    public CategoryVO build() {
        CategoryVO categoryVO = new CategoryVO();
        categoryVO.setId(id);
        categoryVO.setName(name);
        categoryVO.setDescription(description);
        categoryVO.setLevelCategory(levelCategory);
        categoryVO.setItems(items);
        return categoryVO;
    }
}
