package com.aptech.foodmarket.food_market.service;

import com.aptech.foodmarket.food_market.model.Order;
import com.aptech.foodmarket.food_market.vo.CategoryVO;
import com.aptech.foodmarket.food_market.vo.OrderVO;

public interface OrderService {
    public Order createOrder(OrderVO order);
}
