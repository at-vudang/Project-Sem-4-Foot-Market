package com.aptech.foodmarket.food_market.controller;

import com.aptech.foodmarket.food_market.EntityNotFoundException;
import com.aptech.foodmarket.food_market.model.ItemStatisticView;
import com.aptech.foodmarket.food_market.service.ItemService;
import com.aptech.foodmarket.food_market.vo.ItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/statistic")
public class StatisticController {

    @Autowired
    ItemService itemService;
    @RequestMapping(value = "/items",
            method = RequestMethod.GET)
    @ResponseBody
    public Page<ItemStatisticView> getAll(@RequestParam("page") int page,
                               @RequestParam("size") int size,
                               @RequestParam("sort") String sort,
                               @RequestParam(value = "category", required=false) String category,
                               @RequestParam(value = "beginAt", required=false) String beginAt,
                               @RequestParam(value = "endAt", required=false) String endAt
    ) throws EntityNotFoundException {
//        Integer categoryId = Integer.parseInt(category);
        Page<ItemStatisticView> resultPage = itemService.statisticBestSeller(page, size, sort, beginAt,endAt);
        if (page > resultPage.getTotalPages()) {
            new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return resultPage;
    }
}
