package com.aptech.foodmarket.food_market.vo;

import com.aptech.foodmarket.food_market.model.Item;
import com.aptech.foodmarket.food_market.model.Order;

import javax.persistence.*;
import java.util.Date;

public class OrderItemVO {
    private Integer id;

    private Integer quantity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
