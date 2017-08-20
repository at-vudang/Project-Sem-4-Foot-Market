package com.aptech.foodmarket.food_market;

import com.aptech.foodmarket.food_market.model.User;
import com.aptech.foodmarket.food_market.repository.UserRepository;
import com.aptech.foodmarket.food_market.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
//@EnableResourceServer
public class FoodMarketApplication extends SpringBootServletInitializer{

//	@Autowired
//	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(FoodMarketApplication.class, args);
	}

//	public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository repo) throws Exception {
//		builder.userDetailsService(s -> {
//            return new CustomUserDetails(repo.findByUsername(s));
//        });
////				.passwordEncoder(passwordEncoder);
//	}
}
