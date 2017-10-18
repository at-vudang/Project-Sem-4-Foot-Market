package com.aptech.foodmarket.food_market.builder;

import com.aptech.foodmarket.food_market.vo.OrderVO;
import com.aptech.foodmarket.food_market.vo.PaymentVO;

import java.util.Date;

public final class PaymentVOBuilder {
    private Integer id;
    private String transactionId;
    private String payerEmail;
    private Float transactionAmount;
    private Date transactionAt;
    private OrderVO order;

    private PaymentVOBuilder() {
    }

    public static PaymentVOBuilder aPaymentVO() {
        return new PaymentVOBuilder();
    }

    public PaymentVOBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public PaymentVOBuilder withTransactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public PaymentVOBuilder withPayerEmail(String payerEmail) {
        this.payerEmail = payerEmail;
        return this;
    }

    public PaymentVOBuilder withTransactionAmount(Float transactionAmount) {
        this.transactionAmount = transactionAmount;
        return this;
    }

    public PaymentVOBuilder withTransactionAt(Date transactionAt) {
        this.transactionAt = transactionAt;
        return this;
    }

    public PaymentVOBuilder withOrder(OrderVO order) {
        this.order = order;
        return this;
    }

    public PaymentVO build() {
        PaymentVO paymentVO = new PaymentVO();
        paymentVO.setId(id);
        paymentVO.setTransactionId(transactionId);
        paymentVO.setPayerEmail(payerEmail);
        paymentVO.setTransactionAmount(transactionAmount);
        paymentVO.setTransactionAt(transactionAt);
        paymentVO.setOrder(order);
        return paymentVO;
    }
}
