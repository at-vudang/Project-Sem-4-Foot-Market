package com.aptech.foodmarket.food_market.service;

import com.aptech.foodmarket.food_market.model.Promotion;
import com.aptech.foodmarket.food_market.vo.PaymentVO;
import com.aptech.foodmarket.food_market.vo.PromotionVO;
import org.springframework.data.domain.Page;

import java.util.Date;

public interface PaymentService {
    Page<PaymentVO> getAll(int page, int size, String sort);
}
