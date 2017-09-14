package com.aptech.foodmarket.food_market.service.ImplService;

import com.aptech.foodmarket.food_market.builder.ItemVOBuilder;
import com.aptech.foodmarket.food_market.builder.OrderVOBuilder;
import com.aptech.foodmarket.food_market.builder.PromotionItemVOBuilder;
import com.aptech.foodmarket.food_market.model.*;
import com.aptech.foodmarket.food_market.repository.*;
import com.aptech.foodmarket.food_market.service.OrderService;
import com.aptech.foodmarket.food_market.service.PromotionItemService;
import com.aptech.foodmarket.food_market.vo.ItemVO;
import com.aptech.foodmarket.food_market.vo.OrderItemVO;
import com.aptech.foodmarket.food_market.vo.OrderVO;
import com.aptech.foodmarket.food_market.vo.PromotionItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private PromotionItemService promotionItemService;
    private OrderItemServiceImpl orderItemService;
    public OrderVO convertVO(Order order) {
        List<OrderItemVO> orderItemVOS = new ArrayList<>();
        for (OrderItem orderItem:order.getOrderItems()
             ) {
            orderItemVOS.add(orderItemService.convertVO(orderItem));
        }
        OrderVO orderVO = OrderVOBuilder.anOrderVO().withId(order.getId()).withAddress(order.getAddress())
                .withName(order.getName()).withNote(order.getNote())
                .withPhone(order.getPhone()).withPromotionId(order.getPromotion().getId())
                .withShipId(order.getShip().getId()).withTransportedAt(order.getTransportedAt())
                .withUserId(order.getUser().getId()).withOrderItems(orderItemVOS)
                .build();
        return orderVO;
    }
    @Override
    public OrderVO createOrder(OrderVO orderVO) {
        Order order = new Order();
        order.setName(orderVO.getName());
        order.setActive(true);
        order.setAddress(orderVO.getAddress());
        order.setPhone(orderVO.getPhone());
        order.setNote(orderVO.getNote());
        order.setStatus(Byte.parseByte("2"));
        order.setTransportedAt(orderVO.getTransportedAt());
        User user = userRepository.findOne(orderVO.getUserId());
        order.setUser(user);
        order.setPromotion(new Promotion(1));
        order.setShip(new Ship(1));
        order = orderRepository.save(order);
        for (OrderItemVO itemOrder: orderVO.getOrderItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setActive(true);
            Item item = itemRepository.findOne(itemOrder.getIdItem());
            orderItem.setItem(item);
            Double priceOffical = item.getPrice().doubleValue();
            try {
                PromotionItemVO promotionItemVO =
                        promotionItemService.getPromotionItemByPromotionAndItem(
                                orderVO.getPromotionId(),item.getId()
                        );
                Double percent = (promotionItemVO.getPercent()/100.0);
                priceOffical = priceOffical - priceOffical * percent;
            } catch (Exception ex) {
            }
            orderItem.setPriceOffical(priceOffical);
            orderItem.setQuantity(itemOrder.getQuantityCart());
            orderItemRepository.save(orderItem);
            item.setQuantity(item.getQuantity() - itemOrder.getQuantityCart());
            itemRepository.save(item);

        }
        return convertVO(orderRepository.save(order));
    }
}
