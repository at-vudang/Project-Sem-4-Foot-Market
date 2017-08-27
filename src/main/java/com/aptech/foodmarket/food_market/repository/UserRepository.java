package com.aptech.foodmarket.food_market.repository;

import com.aptech.foodmarket.food_market.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by nydiarra on 06/05/17.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>, UserRepositoryCustom {
    User findByUsername(String username);
    @Query(value = "SELECT * FROM USERS WHERE EMAIL_ADDRESS = ?1", nativeQuery = true)
    User findByEmailAddress(String emailAddress);
}
