package com.aptech.foodmarket.food_market.vo;

import java.util.List;

public class CategoryVO {
    private Integer id;
    private String name;
    private String description;
    private Integer levelCategory;
    private List<ItemVO> items;

    public List<ItemVO> getItems() {
        return items;
    }

    public void setItems(List<ItemVO> items) {
        this.items = items;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLevelCategory() {
        return levelCategory;
    }

    public void setLevelCategory(Integer levelCategory) {
        this.levelCategory = levelCategory;
    }
}
