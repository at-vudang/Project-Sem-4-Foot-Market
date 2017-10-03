package com.aptech.foodmarket.food_market.controller;

import com.aptech.foodmarket.food_market.EntityNotFoundException;
import com.aptech.foodmarket.food_market.model.Promotion;
import com.aptech.foodmarket.food_market.service.PromotionItemService;
import com.aptech.foodmarket.food_market.service.PromotionService;
import com.aptech.foodmarket.food_market.vo.PromotionItemVO;
import com.aptech.foodmarket.food_market.vo.PromotionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public PromotionVO getPromotionById(@PathVariable Integer id) throws EntityNotFoundException {
        return  promotionService.getById(id);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    public void deleteItem(@PathVariable Integer id) {
        promotionItemService.deleteItem(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "")
    public ResponseEntity<PromotionVO> createPromotion(@RequestBody Promotion promotion) {
        return new ResponseEntity<PromotionVO>(promotionService.create(promotion), HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.PUT, value = "")
    public ResponseEntity<PromotionVO> updatePromotion(@RequestBody Promotion promotion) {
        return new ResponseEntity<PromotionVO>(promotionService.update(promotion), HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/all")
    @ResponseBody
    public Page<PromotionVO> getPromotion(@RequestParam int page,
                                          @RequestParam int size,
                                          @RequestParam(value = "sort", required=false) String sort) {
        return promotionService.getAll(page,size,sort);
    }

}
