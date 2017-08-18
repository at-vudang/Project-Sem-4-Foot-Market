package com.aptech.foodmarket.food_market.controller;

import com.aptech.foodmarket.food_market.model.User;
import com.aptech.foodmarket.food_market.repository.UserRepository;
import com.aptech.foodmarket.food_market.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @GetMapping(value="/")
    public String home() {
        User user = new User();
        user.setUsername("vudang");
        user.setPassword("vudang");
        userService.save(user);
        return userService.findByUserName("vudang").getPassword();
    }

    @GetMapping(value="/private")
    public String privateArea() {
        return "Private";
    }
}
