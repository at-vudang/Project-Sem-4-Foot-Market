package com.aptech.foodmarket.food_market.repository;

import com.aptech.foodmarket.food_market.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionItemRepository extends JpaRepository<Category,Integer>{
}
