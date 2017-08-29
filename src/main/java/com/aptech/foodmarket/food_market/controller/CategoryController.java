package com.aptech.foodmarket.food_market.controller;

import com.aptech.foodmarket.food_market.service.CategoryService;
import com.aptech.foodmarket.food_market.service.ItemService;
import com.aptech.foodmarket.food_market.vo.CategoryVO;
import com.aptech.foodmarket.food_market.vo.ItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping("/level/{level}")
    @ResponseBody
    public List<CategoryVO> getCategoriesByLevel(@PathVariable Integer level) {
        return categoryService.getCategoriesByLevel(level);
    }

    @RequestMapping("/parents/{parentID}")
    @ResponseBody
    public List<CategoryVO> getCategoriesByParent(@PathVariable Integer parentID) {
        return categoryService.getCategoriesByParent(parentID);
    }
}
