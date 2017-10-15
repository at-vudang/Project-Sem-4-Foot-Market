package com.aptech.foodmarket.food_market.repository;

import com.aptech.foodmarket.food_market.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {
}
