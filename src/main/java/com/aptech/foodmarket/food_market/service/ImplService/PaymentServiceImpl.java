package com.aptech.foodmarket.food_market.service.ImplService;

import com.aptech.foodmarket.food_market.builder.PaymentVOBuilder;
import com.aptech.foodmarket.food_market.builder.PromotionVOBuilder;
import com.aptech.foodmarket.food_market.model.Item;
import com.aptech.foodmarket.food_market.model.Payment;
import com.aptech.foodmarket.food_market.repository.PaymentRepository;
import com.aptech.foodmarket.food_market.service.PaymentService;
import com.aptech.foodmarket.food_market.vo.ItemVO;
import com.aptech.foodmarket.food_market.vo.OrderVO;
import com.aptech.foodmarket.food_market.vo.PaymentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    private OrderServiceImpl orderService;
    @Override
    public Page<PaymentVO> getAll(int page, int size, String sort) {
        Page<Payment> payments;
        if (sort != null && !sort.equals("") && (sort.charAt(0) == ' ' || sort.charAt(0) == '-')) {
            String direction = sort.substring(0,1);
            String keySort = sort.substring(1,sort.length());
            payments = paymentRepository.findAll(new PageRequest(page,size,
                    direction.equals("-") ? Sort.Direction.DESC : Sort.Direction.ASC, keySort));
        } else {
            payments = paymentRepository.findAll(new PageRequest(page, size));
        }
        Page<PaymentVO> paymentVOS = payments.map(new Converter<Payment, PaymentVO>() {
            @Override
            public PaymentVO convert(Payment entity) {
                return convertVO(entity);
            }
        });
        return paymentVOS;
    }
    public PaymentVO convertVO(Payment payment) {
        OrderVO orderVO = new OrderVO();
        orderVO.setId(payment.getOrder().getId());
        PaymentVO paymentVO = PaymentVOBuilder.aPaymentVO().withId(payment.getId())
                .withPayerEmail(payment.getPayerEmail())
                .withTransactionAmount(payment.getTransactionAmount())
                .withTransactionAt(payment.getTransactionAt())
                .withTransactionId(payment.getTransactionId())
                .withOrder(orderVO).build();
        return paymentVO;
    }
}
