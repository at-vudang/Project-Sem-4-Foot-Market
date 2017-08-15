package com.aptech.foodmarket.food_market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class FoodMarketApplication extends SpringBootServletInitializer{
	public static void main(String[] args) {
		SpringApplication.run(FoodMarketApplication.class, args);
	}
}
