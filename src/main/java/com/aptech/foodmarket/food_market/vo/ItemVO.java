package com.aptech.foodmarket.food_market.vo;

import java.util.Date;

public class ItemVO {

    private Integer id;
    private String name;
    private Float price;
    private String avatar;
    private Boolean status;
    private Integer quantity;
    private Date createdAt;
    private Date editedAt;
    private Boolean active;

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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
