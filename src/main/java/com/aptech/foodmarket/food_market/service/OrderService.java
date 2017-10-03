package com.aptech.foodmarket.food_market.service;

import com.aptech.foodmarket.food_market.model.Order;
import com.aptech.foodmarket.food_market.model.User;
import com.aptech.foodmarket.food_market.vo.CategoryVO;
import com.aptech.foodmarket.food_market.vo.OrderVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface OrderService {
    public OrderVO createOrder(OrderVO order) throws Exception;
    public Page<OrderVO> getOrderByUser(User user, int page, int size, String sort);
    public Page<OrderVO> getOrderByStatus(byte status, int page, int size, String sort);
    OrderVO deleteItem(int id);
    OrderVO updateStatus(int id, byte status);
    OrderVO getByID(Integer id);

}
