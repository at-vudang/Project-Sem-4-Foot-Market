package com.aptech.foodmarket.food_market.controller;

import com.aptech.foodmarket.food_market.BaoKimPayment;
import com.aptech.foodmarket.food_market.EntityNotFoundException;
import com.aptech.foodmarket.food_market.Payment;
import com.aptech.foodmarket.food_market.model.Order;
import com.aptech.foodmarket.food_market.model.User;
import com.aptech.foodmarket.food_market.repository.UserRepository;
import com.aptech.foodmarket.food_market.security.JwtTokenUtil;
import com.aptech.foodmarket.food_market.security.JwtUser;
import com.aptech.foodmarket.food_market.service.OrderItemService;
import com.aptech.foodmarket.food_market.service.OrderService;
import com.aptech.foodmarket.food_market.vo.ItemVO;
import com.aptech.foodmarket.food_market.vo.OrderItemVO;
import com.aptech.foodmarket.food_market.vo.OrderVO;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/orderItem")
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/getItemByOrder/{id}")
    @ResponseBody
    public List<OrderItemVO> getItemByOrder(@PathVariable Integer id, HttpServletRequest request)  throws EntityNotFoundException {
//        throw new javax.persistence.EntityNotFoundException();
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getEmailFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        System.out.println(user.getId());
        Order order = new Order();
        order.setId(id);
        User userOrder = userRepository.findByOrders(order);
        if (userOrder.getId() == user.getId()) {
            return orderItemService.getItemByOrder(id);
        }
        return new ArrayList<>();
    }
}
