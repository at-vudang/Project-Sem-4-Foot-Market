package com.aptech.foodmarket.food_market;

import com.aptech.foodmarket.food_market.controller.CategoryController;
import com.aptech.foodmarket.food_market.crawler.BasicWebCrawler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class FoodMarketApplication extends SpringBootServletInitializer{
	public static void main(String[] args) {

		SpringApplication.run(FoodMarketApplication.class, args);
//		new BasicWebCrawler().getCategory("https://www.adayroi.com/do-tuoi-song-vinmart-vingroup-v2282-465?cids=639");
	}
}
