package com.aptech.foodmarket.food_market.repository;

import com.aptech.foodmarket.food_market.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface CategoryRepository extends JpaRepository<Category,Integer>{
 //public List<Category> findAllByIdAnd
}
