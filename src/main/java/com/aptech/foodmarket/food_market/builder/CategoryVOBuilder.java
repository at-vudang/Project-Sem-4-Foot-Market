package com.aptech.foodmarket.food_market.builder;

import com.aptech.foodmarket.food_market.model.LevelCategory;
import com.aptech.foodmarket.food_market.vo.CategoryVO;

import java.util.Date;

public final class CategoryVOBuilder {
    private Integer id;
    private String name;
    private String description;
    private Date createdAt;
    private Date editedAt;
    private LevelCategory levelCategory;

    public LevelCategory getLevelCategory() {
        return levelCategory;
    }

    public void setLevelCategory(LevelCategory levelCategory) {
        this.levelCategory = levelCategory;
    }

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

    public CategoryVOBuilder withCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public CategoryVOBuilder withEditedAt(Date editedAt) {
        this.editedAt = editedAt;
        return this;
    }

//    public CategoryVOBuilder withLevelCategory(LevelCategory levelCategory) {
//        this.levelCategory = levelCategory;
//        return this;
//    }

    public CategoryVO build() {
        CategoryVO categoryVO = new CategoryVO();
        categoryVO.setId(id);
        categoryVO.setName(name);
        categoryVO.setDescription(description);
        categoryVO.setCreatedAt(createdAt);
        categoryVO.setEditedAt(editedAt);
      //  categoryVO.setLevelCategory(levelCategory);
        return categoryVO;
    }
}
