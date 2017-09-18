package com.aptech.foodmarket.food_market.vo;

import com.aptech.foodmarket.food_market.model.OrderItem;
import com.aptech.foodmarket.food_market.model.Promotion;
import com.aptech.foodmarket.food_market.model.Ship;
import com.aptech.foodmarket.food_market.model.User;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class OrderVO {
    private Integer id;
    private String name;
    private String address;
    private String phone;
    private String note;
    private Date transportedAt;
    private Double total;
    private Integer userId;
    private Integer promotionId;
    private Integer shipId;

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    private List<OrderItemVO> orderItems;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getTransportedAt() {
        return transportedAt;
    }

    public void setTransportedAt(Date transportedAt) {
        this.transportedAt = transportedAt;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }

    public Integer getShipId() {
        return shipId;
    }

    public void setShipId(Integer shipId) {
        this.shipId = shipId;
    }

    public List<OrderItemVO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemVO> orderItems) {
        this.orderItems = orderItems;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
