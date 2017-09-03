package com.aptech.foodmarket.food_market.service.ImplService;

import com.aptech.foodmarket.food_market.builder.CategoryVOBuilder;
import com.aptech.foodmarket.food_market.model.Category;
import com.aptech.foodmarket.food_market.model.Item;
import com.aptech.foodmarket.food_market.repository.CategoryRepository;
import com.aptech.foodmarket.food_market.repository.ItemRepository;
import com.aptech.foodmarket.food_market.service.CategoryService;
import com.aptech.foodmarket.food_market.service.ItemService;
import com.aptech.foodmarket.food_market.service.ItemServiceImpl;
import com.aptech.foodmarket.food_market.vo.CategoryVO;
import com.aptech.foodmarket.food_market.vo.ItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<CategoryVO> getCategoriesByLevel(Integer level) {
        List<Category> categories = categoryRepository.findByLevelCategory(level);
        return this.defaultJson(categories);
    }

    public List<CategoryVO> defaultJson(List<Category> categories) {

        List<CategoryVO> categoryVOS = new ArrayList<>();
        categories.stream().forEach(item -> {
            categoryVOS.add(CategoryVOBuilder.aCategoryVO().withId(item.getId())
                    .withName(item.getName())
                    .withDescription(item.getDescription())
                    .withName(item.getName()).withQuantityItems(item.getItems().size())
                    .build());
        });
        return categoryVOS;
    }

    @Override
    public List<CategoryVO> getCategoriesByParent(Integer parentID) {
        List<Category> categories = categoryRepository.findByParentId(parentID);
        return this.defaultJson(categories);
    }

    @Override
    public CategoryVO getCategoryById(Integer id) {
        Category category = categoryRepository.findOne(id);
        ItemServiceImpl itemService = new ItemServiceImpl();
        itemService.defaultJson(category.getItems());
        List<ItemVO> itemVOS = new ArrayList<>();

        CategoryVO categoryVO = CategoryVOBuilder.aCategoryVO().withId(id)
                .withName(category.getName())
                .withDescription(category.getDescription())
                .withName(category.getName()).withItems(itemVOS)
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
        Page<Item> items = itemRepository.findAllByCategories(category, new PageRequest(page, size));
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
}
