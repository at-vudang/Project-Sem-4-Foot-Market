package com.aptech.foodmarket.food_market.service.ImplService;

import com.aptech.foodmarket.food_market.builder.ItemVOBuilder;
import com.aptech.foodmarket.food_market.builder.OrderVOBuilder;
import com.aptech.foodmarket.food_market.builder.PromotionItemVOBuilder;
import com.aptech.foodmarket.food_market.model.*;
import com.aptech.foodmarket.food_market.repository.*;
import com.aptech.foodmarket.food_market.service.OrderService;
import com.aptech.foodmarket.food_market.service.PromotionItemService;
import com.aptech.foodmarket.food_market.utils.OrderStatus;
import com.aptech.foodmarket.food_market.vo.ItemVO;
import com.aptech.foodmarket.food_market.vo.OrderItemVO;
import com.aptech.foodmarket.food_market.vo.OrderVO;
import com.aptech.foodmarket.food_market.vo.PromotionItemVO;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    @Autowired
    private OrderItemServiceImpl orderItemService;

    public OrderVO convertVO(Order order) {
        Double total = 0.0;
        List<OrderItemVO> orderItemVOS = new ArrayList<>();
        if (order.getOrderItems() != null) {
            for (OrderItem orderItem:order.getOrderItems()
                    ) {
                orderItemVOS.add(orderItemService.convertVO(orderItem));
                if (orderItem.getPriceOffical() != null) {
                    total += orderItem.getPriceOffical() * orderItem.getQuantity();
                }
            }
        }
        OrderVO orderVO = OrderVOBuilder.anOrderVO().withId(order.getId()).withAddress(order.getAddress())
                .withName(order.getName()).withNote(order.getNote())
                .withPhone(order.getPhone()).withPromotionId(order.getPromotion().getId())
                .withShipId(order.getShip().getId()).withTransportedAt(order.getTransportedAt())
                .withUserId(order.getUser().getId()).withOrderItems(orderItemVOS)
                .withTotal(total).withCreatedAt(order.getCreatedAt())
                .withStatus(order.getStatus())
                .build();
        return orderVO;
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderVO createOrder(OrderVO orderVO) throws Exception {
        Order order = new Order();
        order.setName(orderVO.getName());
        order.setActive(true);
        order.setAddress(orderVO.getAddress());
        order.setPhone(orderVO.getPhone());
        order.setNote(orderVO.getNote());
        order.setStatus(Byte.parseByte(OrderStatus.Pending.getValue()));
        order.setTransportedAt(orderVO.getTransportedAt());
        User user = userRepository.findOne(orderVO.getUserId());
        order.setUser(user);
        order.setPromotion(new Promotion(1));
        order.setShip(new Ship(1));
        List<OrderItem> orderItems = new ArrayList<>();
        order = orderRepository.save(order);
        for (OrderItemVO itemOrder: orderVO.getOrderItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setActive(true);
            Item item = itemRepository.findOne(itemOrder.getId());
            orderItem.setItem(item);
            Double priceOffical = item.getPrice().doubleValue();
            PromotionItemVO promotionItemVO =
                    promotionItemService.getPromotionItemByPromotionAndItem(
                            orderVO.getPromotionId(),item.getId()
                    );
            if (promotionItemVO.getId() != null) {
                Double percent = (promotionItemVO.getPercent()/100.0);
                priceOffical = priceOffical - priceOffical * percent;
            }
            orderItem.setPriceOffical(priceOffical);
            orderItem.setQuantity(itemOrder.getQuantityCart());
            orderItemRepository.save(orderItem);
            orderItems.add(orderItem);
            item.setQuantity(item.getQuantity() - itemOrder.getQuantityCart());
            itemRepository.save(item);

        }
        order.setOrderItems(orderItems);
        return convertVO(order);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderVO updateStatus(int id, byte status) {
        Order order = orderRepository.findOne(id);
        order.setStatus(status);
        orderRepository.save(order);
        order = orderRepository.findOne(id);
        return convertVOWithoutOrderItem(order);
    }

    @Override
    public Page<OrderVO> getOrderByUser(User user, int page, int size, String sort) {
        Page<Order> orders;
        if (sort != null && !sort.equals("") && (sort.charAt(0) == ' ' || sort.charAt(0) == '-')) {
            String direction = sort.substring(0,1);
            String keySort = sort.substring(1,sort.length());
            orders = orderRepository.findAllByUser(user, new PageRequest(page, size,
                    direction.equals("-") ? Sort.Direction.DESC : Sort.Direction.ASC,
                    keySort));
        } else {
            orders = orderRepository.findAllByUser(user, new PageRequest(page, size));
        }
        Page<OrderVO> orderVOS = orders.map(new Converter<Order, OrderVO>() {
            @Override
            public OrderVO convert(Order order) {
                return convertVOWithoutOrderItem(order);
            }
        });
        return orderVOS;
    }

    @Override
    public Page<OrderVO> getOrderByStatus(byte status, int page, int size, String sort) {
        Page<Order> orders;
        String direction, keySort;
        if (sort != null && !sort.equals("") && (sort.charAt(0) == ' ' || sort.charAt(0) == '-')) {
             direction = sort.substring(0,1);
             keySort = sort.substring(1,sort.length());
//             if (keySort.equals("total")) {
//                 orders = orderRepository.findAllByStatus(status, new PageRequest(page, size));
//                 Page<OrderVO> orderVOS = orders.map(new Converter<Order, OrderVO>() {
//                     @Override
//                     public OrderVO convert(Order order) {
//                         return convertVOWithoutOrderItem(order);
//                     }
//                 });
//             }
            orders = orderRepository.findAllByStatus(status, new PageRequest(page, size,
                    direction.equals("-") ? Sort.Direction.DESC : Sort.Direction.ASC,
                    keySort));
        } else {
            orders = orderRepository.findAllByStatus(status, new PageRequest(page, size));
        }
        if (orders != null) {
            Page<OrderVO> orderVOS = orders.map(new Converter<Order, OrderVO>() {
                @Override
                public OrderVO convert(Order order) {
                    return convertVOWithoutOrderItem(order);
                }
            });
            return orderVOS;
        }
        return null;
    }

    public OrderVO convertVOWithoutOrderItem(Order order) {
        Double total = 0.0;
        if (order.getOrderItems() != null) {
            for (OrderItem orderItem: order.getOrderItems()
                    ) {
                if (orderItem.getPriceOffical() != null) {
                    total += orderItem.getPriceOffical();
                }
            }
        }
        OrderVO orderVO = OrderVOBuilder.anOrderVO().withId(order.getId()).withAddress(order.getAddress())
                .withName(order.getName()).withNote(order.getNote())
                .withPhone(order.getPhone()).withPromotionId(order.getPromotion().getId())
                .withShipId(order.getShip().getId()).withTransportedAt(order.getTransportedAt())
                .withUserId(order.getUser().getId())
                .withCreatedAt(order.getCreatedAt())
                .withTotal(total).withStatus(order.getStatus())
                .build();
        return orderVO;
    }

    @Override
    public OrderVO getByID(Integer id) {
        return convertVO(orderRepository.findOne(id));
    }

    @Override
    public OrderVO deleteItem(int id) {
        Order order = orderRepository.findOne(id);
        OrderVO orderVO = this.convertVO(order);
        orderRepository.delete(id);
        return orderVO;
    }
}
