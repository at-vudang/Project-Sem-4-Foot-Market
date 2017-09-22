package com.aptech.foodmarket.food_market.service.ImplService;

import com.aptech.foodmarket.food_market.builder.CategoryVOBuilder;
import com.aptech.foodmarket.food_market.model.Category;
import com.aptech.foodmarket.food_market.model.Item;
import com.aptech.foodmarket.food_market.repository.CategoryRepository;
import com.aptech.foodmarket.food_market.repository.ItemRepository;
import com.aptech.foodmarket.food_market.service.CategoryService;

import com.aptech.foodmarket.food_market.vo.CategoryVO;
import com.aptech.foodmarket.food_market.vo.ItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Set<CategoryVO> getCategoriesByLevel(Integer level) {
        Set<Category> categories = categoryRepository.findByLevelCategory(level);
        return this.defaultJson(categories);
    }

    public Set<CategoryVO> defaultJson(Set<Category> categories) {

        Set<CategoryVO> categoryVOS = new HashSet<>();
        categories.stream().forEach(item -> {
            categoryVOS.add(CategoryVOBuilder.aCategoryVO().withId(item.getId())
                    .withName(item.getName())
                    .withDescription(item.getDescription())
                    .withName(item.getName()).withQuantityItems(item.getItems().size())
                    .withParentId(item.getParentId())
                    .build());
        });
        return categoryVOS;
    }

    @Override
    public Set<CategoryVO> getCategoriesByParent(Integer parentID) {
        Set<Category> categories = categoryRepository.findByParentId(parentID);
        return this.defaultJson(categories);
    }

    @Override
    public CategoryVO getCategoryById(Integer id) {
        Category category = categoryRepository.findOne(id);
        CategoryVO categoryVO = CategoryVOBuilder.aCategoryVO().withId(id)
                .withName(category.getName())
                .withDescription(category.getDescription())
                .withName(category.getName()).withQuantityItems(category.getItems().size())
                .withParentId(category.getParentId())
                .build();
        return categoryVO;
    }
    @Autowired
    private ItemRepository itemRepository;
    @Override
    public Page<ItemVO> getItems(Integer id, int page, int size) {
        Category category = categoryRepository.findOne(id);
        ItemServiceImpl itemService = new ItemServiceImpl();
        PageRequest pageRequest = new PageRequest(page,size);
        Page<Item> items = itemRepository.findAllByCategoriesIsContaining(category, new PageRequest(page, size));
        Page<ItemVO> itemsVOs = items.map(new Converter<Item, ItemVO>() {
            @Override
            public ItemVO convert(Item entity) {
                return itemService.convertVO(entity);
            }
        });
        return itemsVOs;
    }

    @Override
    public Page<ItemVO> getItems(Integer id, int page, int size, String sort) {

        Category category = categoryRepository.findOne(id);
        ItemServiceImpl itemService = new ItemServiceImpl();
        Page<Item> items;
        if (sort != null && sort.equals("") && (sort.charAt(0) == '+' || sort.charAt(0) == '-')) {
            String direction = sort.substring(0,1);
            String keySort = sort.substring(1,sort.length());
            items = itemRepository.findAllByCategoriesIsContaining(category, new PageRequest(page, size,
                    direction.equals("-") ? Sort.Direction.DESC : Sort.Direction.ASC,
                    keySort));
        } else {
            items = itemRepository.findAllByCategoriesIsContaining(category, new PageRequest(page, size));
        }
        Page<ItemVO> itemsVOs = items.map(new Converter<Item, ItemVO>() {
            @Override
            public ItemVO convert(Item entity) {
                return itemService.convertVO(entity);
            }
        });
        return itemsVOs;

//        return itemService.defaultJson(category.getItems(new PageRequest(page, size)));
    }

    @Override
    public Category create(Category categorie) {
        try {
            Category category = new Category();
            category.setName(categorie.getName());
            category.setActive(true);
            category.setLevelCategory(categorie.getLevelCategory());
            category.setParentId(categorie.getParentId());
            categoryRepository.save(category);
            return category;
        } catch (Exception ex) {
            return null;
        }

    }
    @Override
    public CategoryVO deleteItem(int id) {
        Category cate = categoryRepository.findOne(id);
        CategoryVO categoryVO = this.convertVO(cate);
        categoryRepository.delete(id);
        return categoryVO;
    }

    public CategoryVO convertVO(Category category) {
        CategoryVO categoryVO = CategoryVOBuilder.aCategoryVO()
                .withId(category.getId())
                .withLevelCategory(category.getLevelCategory())
                .withParentId(category.getParentId())
                .withDescription(category.getDescription())
                .withName(category.getName())
                .build();
        return categoryVO;
    }
}
