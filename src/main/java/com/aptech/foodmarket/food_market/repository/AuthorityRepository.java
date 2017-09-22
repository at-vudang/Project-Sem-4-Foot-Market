package com.aptech.foodmarket.food_market.repository;

import com.aptech.foodmarket.food_market.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface AuthorityRepository extends JpaRepository<Authority,Integer>{
}
