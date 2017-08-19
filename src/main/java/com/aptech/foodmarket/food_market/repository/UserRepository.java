package com.aptech.foodmarket.food_market.repository;

import com.aptech.foodmarket.food_market.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by nydiarra on 06/05/17.
 */
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {
   // User findByUsername(String username);
}
