package com.aptech.foodmarket.food_market.repository;

import com.aptech.foodmarket.food_market.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Integer> {
}
