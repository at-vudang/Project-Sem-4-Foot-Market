package com.aptech.foodmarket.food_market.repository;

import com.aptech.foodmarket.food_market.model.Category;
import com.aptech.foodmarket.food_market.model.Promotion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion,Integer> {
    Page<Promotion> findByFromAtGreaterThanEqualOrEndAtIsLessThanEqual(Date start, Date end, Pageable pageable);
}
