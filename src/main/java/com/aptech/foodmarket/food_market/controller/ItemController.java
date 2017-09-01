package com.aptech.foodmarket.food_market.controller;

import com.aptech.foodmarket.food_market.model.Item;
import com.aptech.foodmarket.food_market.model.User;
import com.aptech.foodmarket.food_market.repository.CategoryRepository;
import com.aptech.foodmarket.food_market.repository.ItemRepository;
import com.aptech.foodmarket.food_market.repository.OrderItemRepository;
import com.aptech.foodmarket.food_market.service.ItemService;
import com.aptech.foodmarket.food_market.vo.ItemVO;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ItemService itemService;

    @RequestMapping(value = "/all",params = { "page", "size" },
            method = RequestMethod.GET)
    @ResponseBody
    public Page<ItemVO> getAll(@RequestParam("page") int page, @RequestParam("size") int size) {

        Page<ItemVO> resultPage = itemService.findPaginated(page, size);
//        if (page > resultPage.getTotalPages()) {
//            throw new NotFoundException();
//        }
        return resultPage;
        //return itemService.getAll();
    }

    @RequestMapping("/getItemByName")
    @ResponseBody
    public List<ItemVO> getItemByName(String name) {
        return itemService.getItemByName(name);
    }

    @RequestMapping("/getItemByCate")
    @ResponseBody
    public List<ItemVO> getItemByCate(int cate_id) {
        return itemService.getItemByCategory(categoryRepository.findOne(cate_id));
    }

    @RequestMapping("/getItemById")
    @ResponseBody
    public Item getItemByName(int id) {
        return itemRepository.findOne(id);
    }

    @RequestMapping("/getItemNew")
    @ResponseBody
    public List<ItemVO> getItemNew(int quantity) {
        return itemService.getItemNew(quantity);
    }

    @RequestMapping("/getItemPromotion")
    @ResponseBody
    public List<ItemVO> getItemPromotion(int quantity) {
        return itemService.getItemPromotion(quantity);
    }

    @RequestMapping("/getItemBest")
    @ResponseBody
    public List<ItemVO> getItemBest() {
//        return orderItemRepository.getIDBest();
        return itemService.getItemBestSeller(orderItemRepository.getIDBest());
    }
}
