package com.aptech.foodmarket.food_market.vo;

import com.aptech.foodmarket.food_market.model.Item;
import com.aptech.foodmarket.food_market.model.Order;

import javax.persistence.*;
import java.util.Date;

public class OrderItemVO {
    private Integer id;
    private Integer idItem;
    private ItemVO itemVO;
    private Integer quantityCart;
    private Double priceOffical;
    private Date createAt;

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPriceOffical() {
        return priceOffical;
    }

    public void setPriceOffical(Double priceOffical) {
        this.priceOffical = priceOffical;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer id) {
        this.idItem = id;
    }

    public Integer getQuantityCart() {
        return quantityCart;
    }

    public void setQuantityCart(Integer quantity) {
        this.quantityCart = quantity;
    }


    public ItemVO getItemVO() {
        return itemVO;
    }

    public void setItemVO(ItemVO itemVO) {
        this.itemVO = itemVO;
    }
}
