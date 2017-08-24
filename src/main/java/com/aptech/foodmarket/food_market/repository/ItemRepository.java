package com.aptech.foodmarket.food_market.repository;

import com.aptech.foodmarket.food_market.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Integer> {

    public List<Item> findByName(String name);

    public List<Item> findItemsByCategories(int cateId);

//    @Query("SELECT i FROM items i join categories c on i.where i.name LIKE %:key% or i.name LIKE %:key%")
//    List<Item> findByItemNameOrCategory(@Param("key") String key);

    public List<Item> findAllByOrderByIdDesc();
}
