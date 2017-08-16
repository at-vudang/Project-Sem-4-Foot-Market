package com.aptech.foodmarket.food_market.repository;

import com.aptech.foodmarket.food_market.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by nydiarra on 06/05/17.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
