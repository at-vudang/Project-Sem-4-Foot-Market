package com.aptech.foodmarket.food_market.service.ImplService;

import com.aptech.foodmarket.food_market.builder.UserVOBuilder;
import com.aptech.foodmarket.food_market.model.Authority;
import com.aptech.foodmarket.food_market.model.User;
import com.aptech.foodmarket.food_market.repository.UserRepository;
import com.aptech.foodmarket.food_market.service.UserService;
import com.aptech.foodmarket.food_market.vo.UserVO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private UserRepository userRepository;

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public void save(User user){
        user.setPassword(getPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }
    @Override
    public User createUser(UserVO userVO) {
        User user = new User();
        user.setUsername(userVO.getUsername());
        user.setPassword(userVO.getPassword());
        user.setFullName(userVO.getFullName());
        user.setAddress(userVO.getAddress());
        user.setBirthday(userVO.getBirthday());
        user.setEmail(userVO.getEmail());
        user.setGender(userVO.getGender());
        user.setActive(true);
        List<Authority> list = new ArrayList<>();
        user.setAuthorities(list);
        return userRepository.save(user);
    }

    @Override
    public Page<UserVO> getAllUser(int page,int size) {
        Page<User> users = userRepository.findAll(new PageRequest(page, size));
        Page<UserVO> userVOS = users.map(new Converter<User, UserVO>() {
            @Override
            public UserVO convert(User entity) {
                return convertVO(entity);
            }
        });
        return userVOS;
    }

    @Override
    public Page<UserVO> getUserByAuthority(Integer authorityID,int page,int size, String sort) {
        String direction = sort.substring(0,1);
        String keySort = sort.substring(1,sort.length());
        Authority authority = new Authority();
        authority.setId(authorityID);
        Page<User> users = userRepository.findAllByAuthorities(authority,
                new PageRequest(page, size,
                        direction.equals("-") ? Sort.Direction.DESC : Sort.Direction.ASC,
                        keySort));
        Page<UserVO> userVOS = users.map(new Converter<User, UserVO>() {
            @Override
            public UserVO convert(User entity) {
                return convertVO(entity);
            }
        });
        return userVOS;
    }
    public UserVO convertVO(User user) {
        UserVO userVO = UserVOBuilder.anUserVO().withId(user.getId())
                .withActive(user.getActive()).withAddress(user.getAddress())
                .withBirthday(user.getBirthday()).withAvatar(user.getAvatar())
                .withEmail(user.getEmail()).withCreditCard(user.getCreditCard())
                .withUsername(user.getUsername())
                .withFullName(user.getFullName()).build();
        return userVO;
    }
}
