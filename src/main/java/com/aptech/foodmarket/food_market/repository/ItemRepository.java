package com.aptech.foodmarket.food_market.repository;

import com.aptech.foodmarket.food_market.model.Category;
import com.aptech.foodmarket.food_market.model.Item;
import com.aptech.foodmarket.food_market.model.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Integer>, PagingAndSortingRepository<Item, Integer>{

    List<Item> findByName(String name);
    Item findById(Integer id);
//    @Query("SELECT i FROM items i join categories c on c.id = i.category_id where i.name LIKE '%:key%' or i.name LIKE '%:key%'")
//    List<Item> findByItemNameOrCategory();
    List<Item> findByNameLikeAndSupplier_Id(String name, Integer id);
    List<Item> findByNameIsLike(String name);
    List<Item> findAllByOrderByIdDesc();

    List<Item> findAllByOrderByPromotionItemsDesc();
//
//    @Query("select * from ")
//    List<Item> getItemBest();

    List<Item> findByIdIn(List<Integer> ids);

    List<Item> findAllByCategoriesIsContaining(Category category);
    Page<Item> findAllByCategoriesIsContaining(Category category,Pageable pageable);

    @Query(value="SELECT * FROM items WHERE MATCH(name,description) AGAINST  (?1 IN NATURAL LANGUAGE MODE) ORDER BY ?#{#pageable}" ,nativeQuery = true)
    Page<Item> search(String key,Pageable pageable);

    @Query(value="SELECT a.* FROM items a , tag_categories b where a.id = b.item_id and b.category_id = ?1 and  MATCH(name,description) AGAINST (?2 IN NATURAL LANGUAGE MODE);" ,nativeQuery = true)
    List<Item> searchWithCategory(int cate_id, String key);

    @Query("select p from #{#entityName} p where p.name = ?1")
    List<Item> findByCategory(String name, Pageable p);

    Page<Item> findAllBySupplier(Supplier supplier, Pageable pageable);

    Page<Item> findAllByStatus(int status, Pageable pageable);
}