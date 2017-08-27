package com.aptech.foodmarket.food_market.repository;

import com.aptech.foodmarket.food_market.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class UserRepositoryImpl implements UserRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void someCustomMethod(User user) {
    }
}
