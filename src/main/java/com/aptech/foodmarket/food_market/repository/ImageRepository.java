package com.aptech.foodmarket.food_market.repository;

import com.aptech.foodmarket.food_market.model.Category;
import com.aptech.foodmarket.food_market.model.ImageItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepository extends JpaRepository<ImageItem,Integer>{

    List<ImageItem> findAllByItem_Id(Integer id);
//
//    @Query(value = "select * from image_items where item_id = 1", nativeQuery = true)
//    List<ImageItem> getimage();
}
