package com.aptech.foodmarket.food_market.service;

import com.aptech.foodmarket.food_market.model.Authority;
import com.aptech.foodmarket.food_market.model.User;
import com.aptech.foodmarket.food_market.vo.UserVO;
import org.springframework.data.domain.Page;

public interface UserService {
    public User createUser(UserVO userVO);
    public UserVO update(String user_token, UserVO userVO);
    public UserVO getDetailUser(String user_token);

    public Page<UserVO> getAllUser(int page, int size);
    public Page<UserVO> getUserByAuthority(Integer authorityID, int page, int size, String sort);

    public UserVO updateUserByAdmin(UserVO userVO);
    public UserVO updatePassword(int id, String newPassword);
}
