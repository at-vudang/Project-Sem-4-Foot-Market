package com.aptech.foodmarket.food_market.vo;

import com.aptech.foodmarket.food_market.model.LevelCategory;

import java.util.Date;

public class CategoryVO {

    private Integer id;
    private String name;
    private String description;
    private Date createdAt;
    private Date editedAt;
  //  private LevelCategory levelCategory;

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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getEditedAt() {
        return editedAt;
    }

    public void setEditedAt(Date editedAt) {
        this.editedAt = editedAt;
    }

//    public LevelCategory getLevelCategory() {
//        return levelCategory;
//    }
//
//    public void setLevelCategory(LevelCategory levelCategory) {
//        this.levelCategory = levelCategory;
//    }
}
