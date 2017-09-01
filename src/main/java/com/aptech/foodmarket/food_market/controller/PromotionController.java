package com.aptech.foodmarket.food_market.controller;

import com.aptech.foodmarket.food_market.model.PromotionItem;
import com.aptech.foodmarket.food_market.repository.PromotionItemRepository;
import com.aptech.foodmarket.food_market.service.CategoryService;
import com.aptech.foodmarket.food_market.service.PromotionItemService;
import com.aptech.foodmarket.food_market.vo.CategoryVO;
import com.aptech.foodmarket.food_market.vo.ItemVO;
import com.aptech.foodmarket.food_market.vo.PromotionItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/promotion")
public class PromotionController {

    @Autowired
    PromotionItemService promotionItemService;

    @RequestMapping("/{id}")
    @ResponseBody
    public PromotionItemVO getPromotionItem(@PathVariable Integer id) {

        PromotionItemVO promotionItemVO = promotionItemService.
                getPromotionItemByPromotionAndItem(1, id);
        return promotionItemVO;
    }

}
