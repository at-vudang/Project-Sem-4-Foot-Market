package com.aptech.foodmarket.food_market.service;

import com.aptech.foodmarket.food_market.EntityNotFoundException;
import com.aptech.foodmarket.food_market.vo.ItemVO;
import com.aptech.foodmarket.food_market.vo.OrderItemVO;

import java.util.List;

public interface OrderItemService {
    public List<OrderItemVO> getItemByOrder(int id) throws EntityNotFoundException;
    OrderItemVO deleteItem(int id);

}
