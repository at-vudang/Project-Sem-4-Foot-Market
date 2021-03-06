package com.aptech.foodmarket.food_market.security.service;

import java.io.Serializable;

/**
 * Created by stephan on 20.03.16.
 */
public class JwtAuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    private final String token;
    private final Long expire;

    public JwtAuthenticationResponse(String token, Long expire) {
        this.expire = expire;
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

    public Long getExpire() {
        return this.expire;
    }
}
