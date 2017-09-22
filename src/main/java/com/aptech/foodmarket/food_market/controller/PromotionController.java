package com.aptech.foodmarket.food_market.controller;

import com.aptech.foodmarket.food_market.model.Promotion;
import com.aptech.foodmarket.food_market.service.PromotionItemService;
import com.aptech.foodmarket.food_market.service.PromotionService;
import com.aptech.foodmarket.food_market.vo.PromotionItemVO;
import com.aptech.foodmarket.food_market.vo.PromotionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/promotion")
public class PromotionController {

    @Autowired
    PromotionItemService promotionItemService;
    @Autowired
    PromotionService promotionService;

    @RequestMapping("/{id}")
    @ResponseBody
    public PromotionItemVO getPromotionItem(@PathVariable Integer id) {

        PromotionItemVO promotionItemVO = promotionItemService.
                getPromotionItemByPromotionAndItem(1, id);
        return promotionItemVO;
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    public void deleteItem(@PathVariable Integer id) {
        promotionItemService.deleteItem(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "")
    public ResponseEntity<PromotionVO> createPromotion(@RequestBody Promotion promotion) {
        return new ResponseEntity<PromotionVO>(promotionService.create(promotion), HttpStatus.OK);
    }

}
