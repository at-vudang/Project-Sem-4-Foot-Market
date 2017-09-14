package com.aptech.foodmarket.food_market.service;

import com.aptech.foodmarket.food_market.model.User;
import com.aptech.foodmarket.food_market.vo.UserVO;
public interface UserService {
    public User createUser(UserVO userVO);
}
