package com.aptech.foodmarket.food_market.utils;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

public abstract class SecurityUtils {
    private static String SYSTEM_WIDE_SALT = "MaYzkSjmkzPC57L";

    public static String encodePassword(String passwordCleartext) {
        ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
        return encoder.encodePassword(passwordCleartext, null);
    }
}
