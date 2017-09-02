package com.aptech.foodmarket.food_market.repository;

import com.aptech.foodmarket.food_market.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Integer>{
    List<Category> findByLevelCategory(Integer level);
    List<Category> findByParentId(Integer parentID);
    Category findById(Integer id);
}
