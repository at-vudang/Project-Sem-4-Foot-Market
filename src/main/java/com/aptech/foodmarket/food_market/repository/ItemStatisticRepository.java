package com.aptech.foodmarket.food_market.repository;

import com.aptech.foodmarket.food_market.model.ItemStatisticProcedure;
import com.aptech.foodmarket.food_market.model.ItemStatisticView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ItemStatisticRepository extends JpaRepository<ItemStatisticView,Integer>, PagingAndSortingRepository<ItemStatisticView, Integer>{
    Page<ItemStatisticView> findAll(Pageable pageable);

    @Query(value="Select h.* from (SELECT a.id ,a.name , d.full_name as `supplier_name`, e.quantity as `quantity_order`," +
            "a.supplier_id, c.user_id " +
            "FROM items a " +
            "JOIN suppliers c " +
            "ON a.supplier_id = c.id " +
            "JOIN users d " +
            "ON c.user_id = d.id " +
            "LEFT JOIN (SELECT b.item_id, SUM(b.quantity) AS quantity  " +
            "FROM  order_items b where b.created_at between ?1 and ?#{#pageable} " +
            "GROUP BY b.item_id) e " +
            "ON a.id = e.item_id) h ORDER BY h.quantity_order desc,?#{#pageable}",
            countQuery = "select count(a.id) from items a", nativeQuery = true)
    Page<ItemStatisticView> getItemStatistic(Date begin, Date end, Pageable pageable);
//    Page<ItemStatisticView> findAllByCreatedAtBetween(Pageable pageable, Date beginAt, Date endAt);

//    @Query(value = "select f.*, g.quantity_order " +
//            "from (SELECT distinct a.id, a.name, d.id as supplier_id, e.id as user_id, e.full_name as supplier_name\n" +
//            "FROM items a \n" +
//            "                join suppliers d\n" +
//            "                on a.supplier_id = d.id\n" +
//            "                join users e\n" +
//            "                on d.user_id = e.id) f\n" +
//            "left join (select distinct b.item_id,  sum(b.quantity) as quantity_order\n" +
//            "from order_items b\n" +
//            "            where b.created_at between ?#{#beginAt} and ?#{#endAt}\n" +
//            "            GROUP BY b.item_id\n" +
//            "            ) g \n" +
//            "on f.id = g.item_id\n" +
//            "order by ?#{#pageable}",nativeQuery = true)
//    Page<ItemStatisticView> getItemStatistic(Pageable pageable,String beginAt,String endAt)
//    Integer getItemStatistic(@Param("arg") Integer arg);
}