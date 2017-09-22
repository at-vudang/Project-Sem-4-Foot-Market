package com.aptech.foodmarket.food_market.controller;

import com.aptech.foodmarket.food_market.service.PromotionItemService;
import com.aptech.foodmarket.food_market.service.UnitService;
import com.aptech.foodmarket.food_market.vo.PromotionItemVO;
import com.aptech.foodmarket.food_market.vo.UnitVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/unit")
public class UnitController {

    @Autowired
    UnitService unitService;

    @RequestMapping("/")
    @ResponseBody
    public List<UnitVO> getUnitItem() {
        return unitService.getAll();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    public void deleteItem(@PathVariable Integer id) {
        unitService.deleteItem(id);
    }
}
