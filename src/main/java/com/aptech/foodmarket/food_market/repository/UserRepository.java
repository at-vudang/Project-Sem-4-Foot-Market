package com.aptech.foodmarket.food_market.repository;

import com.aptech.foodmarket.food_market.model.Authority;
import com.aptech.foodmarket.food_market.model.Category;
import com.aptech.foodmarket.food_market.model.Order;
import com.aptech.foodmarket.food_market.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by nydiarra on 06/05/17.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    @Query(value = "SELECT * FROM USERS WHERE email = ?1", nativeQuery = true)
    User findByEmailAddress(String emailAddress);
    User findByEmail(String email);
    User findByOrders(Order order);
    Page<User> findAllByAuthorities(Authority authority, Pageable pageable);
}
