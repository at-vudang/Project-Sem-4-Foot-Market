package com.aptech.foodmarket.food_market;

import com.aptech.foodmarket.food_market.crawler.BasicWebCrawler;
import com.aptech.foodmarket.food_market.service.CategoryService;
import com.aptech.foodmarket.food_market.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyCommandLineRunner implements ApplicationRunner {

    private CategoryService categoryService;
    private ItemService itemService;
    private BasicWebCrawler basicWebCrawler;

    @Autowired
    public MyCommandLineRunner(CategoryService categoryService, ItemService itemService, BasicWebCrawler basicWebCrawler) {
        this.categoryService = categoryService;
        this.itemService = itemService;
        this.basicWebCrawler = basicWebCrawler;
    }
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
//        itemService.init();
        basicWebCrawler.crawler("https://www.adayroi.com/vinmart");
    }

}
