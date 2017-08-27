package com.aptech.foodmarket.food_market.builder;

import com.aptech.foodmarket.food_market.model.Item;
import com.aptech.foodmarket.food_market.vo.UnitVO;

import java.util.Date;
import java.util.List;

public final class UnitVOBuilder {
    private Integer id;
    private String name;
    private String syntax;
    private List<Item> items;
    private Date createdAt;
    private Date editedAt;
    private Boolean active;

    private UnitVOBuilder() {
    }

    public static UnitVOBuilder anUnitVO() {
        return new UnitVOBuilder();
    }

    public UnitVOBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public UnitVOBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UnitVOBuilder withSyntax(String syntax) {
        this.syntax = syntax;
        return this;
    }

    public UnitVOBuilder withItems(List<Item> items) {
        this.items = items;
        return this;
    }

    public UnitVOBuilder withCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public UnitVOBuilder withEditedAt(Date editedAt) {
        this.editedAt = editedAt;
        return this;
    }

    public UnitVOBuilder withActive(Boolean active) {
        this.active = active;
        return this;
    }

    public UnitVO build() {
        UnitVO unitVO = new UnitVO();
        unitVO.setId(id);
        unitVO.setName(name);
        unitVO.setSyntax(syntax);
        unitVO.setItems(items);
        unitVO.setCreatedAt(createdAt);
        unitVO.setEditedAt(editedAt);
        unitVO.setActive(active);
        return unitVO;
    }
}
