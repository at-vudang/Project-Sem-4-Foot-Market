package com.aptech.foodmarket.food_market.repository;

import com.aptech.foodmarket.food_market.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Integer> {

    List<Item> findByName(String name);

    List<Item> findItemsByCategories(int cateId);

//    @Query("SELECT i FROM items i join categories c on c.id = i.category_id where i.name LIKE '%:key%' or i.name LIKE '%:key%'")
//    List<Item> findByItemNameOrCategory();

    List<Item> findAllByOrderByIdDesc();

    List<Item> findAllByOrderByPromotionItemsDesc();
//
//    @Query("select * from ")
//    List<Item> getItemBest();

    List<Item> findByIdIn(List<Integer> ids);
}
