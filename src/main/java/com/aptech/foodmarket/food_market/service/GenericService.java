package com.aptech.foodmarket.food_market.service;

import com.aptech.foodmarket.food_market.model.RandomCity;
import com.aptech.foodmarket.food_market.model.User;

import java.util.List;

/**
 * Created by nydiarra on 06/05/17.
 */
public interface GenericService {
    User findByUsername(String username);

    List<User> findAllUsers();

    List<RandomCity> findAllRandomCities();
}
