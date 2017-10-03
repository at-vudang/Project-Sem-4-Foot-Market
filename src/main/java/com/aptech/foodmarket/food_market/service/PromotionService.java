package com.aptech.foodmarket.food_market.service;

import com.aptech.foodmarket.food_market.model.Promotion;
import com.aptech.foodmarket.food_market.vo.PromotionItemVO;
import com.aptech.foodmarket.food_market.vo.PromotionVO;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

public interface PromotionService {
    PromotionVO create(Promotion promotion);
    PromotionVO getById(Integer id);
    PromotionVO update(Promotion promotion);
    Page<PromotionVO> getAll(int page, int size, String sort);
    Page<PromotionVO> getByDate(Date date, int page, int size, String sort);
    PromotionVO deleteItem(int id);
}
