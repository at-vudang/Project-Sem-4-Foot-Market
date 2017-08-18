package com.aptech.foodmarket.food_market.service;

import com.aptech.foodmarket.food_market.model.User;
import com.aptech.foodmarket.food_market.repository.UserRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service("userDetailsService")
public class UserService implements UserDetailsService{

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private UserRepository userRepository;

    public void save(User user){
        userRepository.save(user);
    }
    public User findByUserName(String username){
       return userRepository.findByUsername(username);
    }
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s);
    }
}
