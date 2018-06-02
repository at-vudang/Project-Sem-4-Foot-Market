package com.aptech.foodmarket.food_market.utils;

public enum OrderStatus {
    Cancel("0"),Pending("1"),Approved("2"), Finished("3");


    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    OrderStatus(String value) {
        this.value = value;
    }
}
