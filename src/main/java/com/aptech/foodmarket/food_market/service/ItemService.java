package com.aptech.foodmarket.food_market.service;

import com.aptech.foodmarket.food_market.model.Category;
import com.aptech.foodmarket.food_market.model.Item;
import com.aptech.foodmarket.food_market.vo.ItemVO;
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

}
