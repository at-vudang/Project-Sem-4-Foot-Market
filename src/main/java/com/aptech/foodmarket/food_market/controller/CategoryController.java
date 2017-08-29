package com.aptech.foodmarket.food_market.controller;

import com.aptech.foodmarket.food_market.model.Category;
import com.aptech.foodmarket.food_market.repository.CategoryRepository;
import com.aptech.foodmarket.food_market.service.CategoryService;
import com.aptech.foodmarket.food_market.vo.CategoryVO;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping("/")
    @ResponseBody
    public List<CategoryVO> getAllNameCategory() throws SQLGrammarException {

        try {
            return categoryService.getAllNameCategory();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
          return null;
     //   return categoryRepository.findAll();
    }

}
