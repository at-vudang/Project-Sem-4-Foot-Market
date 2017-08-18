package com.aptech.foodmarket.food_market.repository;

import com.aptech.foodmarket.food_market.model.Category;
import com.aptech.foodmarket.food_market.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer>{
}
