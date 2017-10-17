package com.aptech.foodmarket.food_market.vo;

import java.util.Date;

public class PaymentVO {

    private Integer id;
    private String transactionId;
    private String payerEmail;
    private Float transactionAmount;
    private Date transactionAt;
    private OrderVO order;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getPayerEmail() {
        return payerEmail;
    }

    public void setPayerEmail(String payerEmail) {
        this.payerEmail = payerEmail;
    }

    public Float getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Float transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Date getTransactionAt() {
        return transactionAt;
    }

    public void setTransactionAt(Date transactionAt) {
        this.transactionAt = transactionAt;
    }

    public OrderVO getOrder() {
        return order;
    }

    public void setOrder(OrderVO order) {
        this.order = order;
    }
}
