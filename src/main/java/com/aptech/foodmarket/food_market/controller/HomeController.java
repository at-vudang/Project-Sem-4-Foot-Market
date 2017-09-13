package com.aptech.foodmarket.food_market.controller;

import com.aptech.foodmarket.food_market.repository.ItemRepository;
import com.aptech.foodmarket.food_market.repository.UserRepository;
import com.aptech.foodmarket.food_market.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
//    @Autowired
//    private UserService userService;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemService itemService;

    @Autowired
    private UserRepository userRepository;
    @GetMapping(value="/")
    public String home() {
       return "Hello";
    }

    @GetMapping(value="/private")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String privateArea() {
        return "Private";
    }

}
