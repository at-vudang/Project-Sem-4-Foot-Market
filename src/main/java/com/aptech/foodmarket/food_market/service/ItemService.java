package com.aptech.foodmarket.food_market.service;

import com.aptech.foodmarket.food_market.EntityNotFoundException;
import com.aptech.foodmarket.food_market.model.Category;
import com.aptech.foodmarket.food_market.model.Item;
import com.aptech.foodmarket.food_market.model.ItemStatisticView;
import com.aptech.foodmarket.food_market.model.Supplier;
import com.aptech.foodmarket.food_market.vo.CategoryVO;
import com.aptech.foodmarket.food_market.vo.ItemVO;
import com.aptech.foodmarket.food_market.vo.OrderItemVO;
import org.springframework.data.domain.Page;

import java.lang.reflect.Array;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface ItemService {

    public List<ItemVO> getItemByName(String name);

    public List<ItemVO> getAll();

    public List<ItemVO> getItemBestSeller( List<Integer> ids);

    public List<ItemVO> getItemByCategory(Category cate);

    public List<ItemVO> getItemByCategoryOrName( String key);

    public List<ItemVO> getItemPromotion( int quantity);
    public List<ItemVO> getItemTrending( int quantity);
    public List<ItemVO> getItemNew( int quantity);

    public ItemVO getItemById( int id);

    public Page<ItemVO> findPaginated(int page, int size, String sort);

    public List<ItemVO> getCart(List<Integer> itemIds);
    public Item create(Item items);
    public void init();
    Set<CategoryVO> getCategory(Integer id);
    public List<ItemVO> getItemTool(int quantity);

    Page<ItemVO> search(String key, int page, int size);

    List<ItemVO> searchWithCategory(int cate_id, String key);

    List<ItemVO> searchWithSupplierId(Integer id,String key);

    ItemVO createItem(Item item);
    ItemVO updateItem(Item item);

    public Page<ItemVO> getItemBySuplier(Supplier supplier, int page, int size);
    public Page<ItemVO> getItemByStatus(int status, int page, int size);
    Page<ItemStatisticView> statisticBestSeller(int page, int size,String sort,
                                                String beginAt, String endAT);
    ItemVO deleteItem(int id);

}
