package com.aptech.foodmarket.food_market.repository;

import com.aptech.foodmarket.food_market.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface CategoryRepository extends JpaRepository<Category,Integer>{
    Set<Category> findByLevelCategory(Integer level);
    Set<Category> findByParentId(Integer parentID);
    Category findById(Integer id);
    Category findFirstByName(String name);
}
