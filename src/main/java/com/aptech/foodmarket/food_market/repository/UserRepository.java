package com.aptech.foodmarket.food_market.repository;

import com.aptech.foodmarket.food_market.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
