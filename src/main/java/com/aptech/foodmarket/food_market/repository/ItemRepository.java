package com.aptech.foodmarket.food_market.repository;

import com.aptech.foodmarket.food_market.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Integer> {
//    List<Item> findAllById(String Name);
//

}
