package com.aptech.foodmarket.food_market.controller;

import com.aptech.foodmarket.food_market.model.Order;
import com.aptech.foodmarket.food_market.model.Promotion;
import com.aptech.foodmarket.food_market.model.Ship;
import com.aptech.foodmarket.food_market.model.User;
import com.aptech.foodmarket.food_market.service.CategoryService;
import com.aptech.foodmarket.food_market.service.OrderService;
import com.aptech.foodmarket.food_market.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @RequestMapping("/")
    @ResponseBody
    public String getAll() {
        return "hello";
    }
    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<Order> getCategoriesByLevel(@RequestBody OrderVO orderVO) {
        return new ResponseEntity<Order>(orderService.createOrder(orderVO), HttpStatus.OK);
    }
}
