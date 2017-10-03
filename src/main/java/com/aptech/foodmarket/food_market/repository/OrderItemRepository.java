package com.aptech.foodmarket.food_market.repository;

import com.aptech.foodmarket.food_market.model.Category;
import com.aptech.foodmarket.food_market.model.Item;
import com.aptech.foodmarket.food_market.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer>{

//    List<OrderItem> findAllByOrderBy();
//    @Query(value = "select id as sl from order_items")
    @Query(value = "select item_id from (select item_id, count(item_id) as sl from order_items GROUP BY item_id) as temp ORDER by temp.sl", nativeQuery = true)
    List<Integer> getIDBest();

    List<OrderItem> findAllByItem(Item item);
}
