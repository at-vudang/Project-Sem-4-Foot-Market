package com.aptech.foodmarket.food_market.service;

import com.aptech.foodmarket.food_market.builder.CategoryVOBuilder;
import com.aptech.foodmarket.food_market.model.Category;
import com.aptech.foodmarket.food_market.model.LevelCategory;
import com.aptech.foodmarket.food_market.repository.CategoryRepository;
import com.aptech.foodmarket.food_market.vo.CategoryVO;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImqL implements CategoryService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private CategoryRepository categoryRepository;

    //lay name category
    @Override
    public List<CategoryVO> getAllNameCategory() {
        List<CategoryVO> categoryVOS = new ArrayList<>();
        List<Category> categories = categoryRepository.findAll();
        System.out.println(categories);
        categories.stream().forEach(user -> {
            categoryVOS.add(CategoryVOBuilder.aCategoryVO()
                    .withName(user.getName())
               //     .withLevelCategory(user.getLevelCategory())
                    .build());
        });
        return categoryVOS;
    }

}
