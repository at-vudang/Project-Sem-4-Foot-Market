package com.aptech.foodmarket.food_market.controller;

import com.aptech.foodmarket.food_market.crawler.BasicWebCrawler;
import com.aptech.foodmarket.food_market.model.Category;
import com.aptech.foodmarket.food_market.model.Item;
import com.aptech.foodmarket.food_market.repository.CategoryRepository;
import com.aptech.foodmarket.food_market.service.CategoryService;
import com.aptech.foodmarket.food_market.service.ItemService;
import com.aptech.foodmarket.food_market.vo.CategoryVO;
import com.aptech.foodmarket.food_market.vo.ItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Basic;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryRepository categoryRepository;

    @RequestMapping("/level/{level}")
    @ResponseBody
    public Set<CategoryVO> getCategoriesByLevel(@PathVariable Integer level) {
        return categoryService.getCategoriesByLevel(level);
    }

    @RequestMapping("/{id}")
    @ResponseBody
    public CategoryVO getCategoriesById(@PathVariable Integer id) {
        return categoryService.getCategoryById(id);
    }
    @RequestMapping(value = "/items/{id}", params = { "page", "size" })
    @ResponseBody
    public Page<ItemVO> getItemsById(@PathVariable Integer id,
                                     @RequestParam("page") int page,
                                     @RequestParam("size") int size) {
        Page<ItemVO> resultPage = categoryService.getItems(id,page, size);
//        if (page > resultPage.getTotalPages()) {
//            new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
        return resultPage;
    }

    @RequestMapping("/parents/{parentID}")
    @ResponseBody
    public Set<CategoryVO> getCategoriesByParent(@PathVariable Integer parentID) {
        return categoryService.getCategoriesByParent(parentID);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    public void deleteItem(@PathVariable Integer id) {
        categoryService.deleteItem(id);
    }

}
