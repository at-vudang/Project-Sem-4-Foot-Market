package com.aptech.foodmarket.food_market.repository;

import com.aptech.foodmarket.food_market.model.User;
import org.springframework.data.jpa.repository.Query;

public interface UserRepositoryCustom {
    public void someCustomMethod(User user);
}
