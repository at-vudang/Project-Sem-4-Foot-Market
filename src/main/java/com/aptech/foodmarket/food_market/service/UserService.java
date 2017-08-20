package com.aptech.foodmarket.food_market.service;

import com.aptech.foodmarket.food_market.model.User;
import com.aptech.foodmarket.food_market.repository.UserRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private UserRepository userRepository;

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public void save(User user){
        user.setPassword(getPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }
//    public User findByUserName(String username){
//       return userRepository.findByUsername(username);
//    }
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        User user =  userRepository.findByUsername(s);
//        if(user == null) {
//            throw new UsernameNotFoundException(String.format("The username %s doesn't exist", s));
//        }
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority("ADMIN2"));
//        UserDetails userDetails = new org.springframework.security.core.userdetails.
//                User(user.getUsername(), user.getPassword(), authorities);
//
//        return userDetails;
//    }
}
