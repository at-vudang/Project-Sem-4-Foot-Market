package com.aptech.foodmarket.food_market.service;

import com.aptech.foodmarket.food_market.EntityNotFoundException;
import com.aptech.foodmarket.food_market.model.Category;
import com.aptech.foodmarket.food_market.model.Item;
import com.aptech.foodmarket.food_market.vo.CategoryVO;
import com.aptech.foodmarket.food_market.vo.ItemVO;
import com.aptech.foodmarket.food_market.vo.OrderItemVO;
import org.springframework.data.domain.Page;

import java.lang.reflect.Array;
import java.util.List;

public interface ItemService {

    public List<ItemVO> getItemByName(String name);

    public List<ItemVO> getAll();

    public List<ItemVO> getItemBestSeller( List<Integer> ids);

    public List<ItemVO> getItemByCategory(Category cate);

    public List<ItemVO> getItemByCategoryOrName( String key);

    public List<ItemVO> getItemPromotion( int quantity);

    public List<ItemVO> getItemNew( int quantity);

    public ItemVO getItemById( int id);

    public Page<ItemVO> findPaginated(int page, int size);

    public List<ItemVO> getCart(List<Integer> itemIds);
    public Item create(Item items);
    public void init();
    List<CategoryVO> getCategory(Integer id);
    public List<ItemVO> getItemTool(int quantity);

    Page<ItemVO> search(String key, int page, int size);

    List<ItemVO> searchWithCategory(int cate_id, String key);

    Item createItem(Item item);
}
