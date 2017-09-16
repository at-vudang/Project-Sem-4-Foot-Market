package com.aptech.foodmarket.food_market.controller;

import com.aptech.foodmarket.food_market.model.ImageItem;
import com.aptech.foodmarket.food_market.repository.ImageRepository;
import com.aptech.foodmarket.food_market.service.ImageService;
import com.aptech.foodmarket.food_market.vo.ImageItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/images")
public class ImageController {

    @Autowired
    ImageService imageService;

    @RequestMapping("/item/{id}")
    @ResponseBody
    public List<ImageItemVO> getImageByItemId(@PathVariable Integer id) {
        return imageService.getImageOfItem(id);
    }
}
