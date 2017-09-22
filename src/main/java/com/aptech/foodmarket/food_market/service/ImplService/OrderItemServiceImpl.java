package com.aptech.foodmarket.food_market.service.ImplService;

import com.aptech.foodmarket.food_market.EntityNotFoundException;
import com.aptech.foodmarket.food_market.builder.ItemVOBuilder;
import com.aptech.foodmarket.food_market.builder.OrderItemVOBuilder;
import com.aptech.foodmarket.food_market.builder.OrderVOBuilder;
import com.aptech.foodmarket.food_market.builder.PromotionItemVOBuilder;
import com.aptech.foodmarket.food_market.model.*;
import com.aptech.foodmarket.food_market.repository.ItemRepository;
import com.aptech.foodmarket.food_market.repository.OrderItemRepository;
import com.aptech.foodmarket.food_market.repository.OrderRepository;
import com.aptech.foodmarket.food_market.repository.UserRepository;
import com.aptech.foodmarket.food_market.service.ItemService;
import com.aptech.foodmarket.food_market.service.OrderItemService;
import com.aptech.foodmarket.food_market.service.OrderService;
import com.aptech.foodmarket.food_market.service.PromotionItemService;
import com.aptech.foodmarket.food_market.vo.ItemVO;
import com.aptech.foodmarket.food_market.vo.OrderItemVO;
import com.aptech.foodmarket.food_market.vo.OrderVO;
import com.aptech.foodmarket.food_market.vo.PromotionItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemServiceImpl itemService;

    public OrderItemVO convertVO(OrderItem orderItem) {
        OrderItemVO orderItemVO = new OrderItemVO();
        orderItemVO.setId(orderItem.getId());
        orderItemVO.setQuantityCart(orderItem.getQuantity());
        orderItemVO.setPriceOffical(orderItem.getPriceOffical());
        ItemVO itemVO = new ItemVO();
        itemVO = itemService.convertVO(orderItem.getItem());
        orderItemVO.setItemVO(itemVO);
        return orderItemVO;
    }

    @Override
    public List<OrderItemVO> getItemByOrder(int id) throws EntityNotFoundException {
        List<OrderItem> orderItems = orderRepository.findOne(id).getOrderItems();
        List<OrderItemVO> orderItemVOS = new ArrayList<>();
        for (OrderItem orderItem: orderItems
                ) {
            orderItemVOS.add(convertVO(orderItem));
        }
        return orderItemVOS;
    }
    @Override
    public OrderItemVO deleteItem(int id) {
        OrderItem orderItem = orderItemRepository.findOne(id);
        OrderItemVO orderItemVO = this.convertVO(orderItem);
        orderItemRepository.delete(id);
        return orderItemVO;
    }

}
