package com.aptech.foodmarket.food_market.service.ImplService;

import com.aptech.foodmarket.food_market.builder.UserVOBuilder;
import com.aptech.foodmarket.food_market.model.Authority;
import com.aptech.foodmarket.food_market.model.Order;
import com.aptech.foodmarket.food_market.model.User;
import com.aptech.foodmarket.food_market.repository.AuthorityRepository;
import com.aptech.foodmarket.food_market.repository.UserRepository;
import com.aptech.foodmarket.food_market.security.JwtTokenUtil;
import com.aptech.foodmarket.food_market.security.JwtUser;
import com.aptech.foodmarket.food_market.service.UserService;
import com.aptech.foodmarket.food_market.vo.AuthorityVO;
import com.aptech.foodmarket.food_market.vo.OrderVO;
import com.aptech.foodmarket.food_market.vo.UserVO;
import com.sun.org.apache.bcel.internal.generic.RETURN;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private OrderServiceImpl orderService;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public User save(User user) {
        user.setPassword(getPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserVO createUser(UserVO userVO) {
        User user = new User();
        user.setUsername(userVO.getUsername());
        user.setPassword(userVO.getPassword());
        user.setFullName(userVO.getFullName());
        user.setAddress(userVO.getAddress());
        user.setBirthday(userVO.getBirthday());
        user.setCreditCard(userVO.getCreditCard());
        user.setEmail(userVO.getEmail());
        user.setGender(userVO.getGender());
        user.setAvatar(userVO.getAvatar());
        user.setActive(true);
        Set<Authority> authorities = new HashSet<>();
        for (AuthorityVO authorityVO:userVO.getAuthorities()
             ) {
            Authority authority = authorityRepository.findOne(authorityVO.getId());
            authorities.add(authority);
        }
        user.setAuthorities(authorities);
        return convertVO(this.save(user));

    }

    @Override
    public UserVO update(String token, UserVO userVO) {
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if (username != null) {
            // JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
            User user = userRepository.findByUsername(username);
            if (user != null) {
                user.setFullName(userVO.getFullName());
                user.setAddress(userVO.getAddress());
                user.setBirthday(userVO.getBirthday());
                user.setEmail(userVO.getEmail());
                user.setGender(userVO.getGender());
                user.setPhone(userVO.getPhone());
                if (userVO.getAvatar() != null){
                    user.setAvatar(userVO.getAvatar());
                }
                return this.convertVO(userRepository.save(user));
            }
        }
        return null;
    }

    @Override
    public UserVO getDetailUser(String user_token) {
        String username = jwtTokenUtil.getUsernameFromToken(user_token);
        if (username != null) {
            User user = userRepository.findByUsername(username);
            UserVO userVO = convertVO(user);
            return userVO;
        }
        return null;
    }

    //    public User findByUserName(String username){
//       return userRepository.findByUsername(username);
//    }
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        User user =  userRepository.findByUsername(s);
//        if(user == null) {
//            throw new UsernameNotFoundException(String.format("The username %s doesn't exist", s));
//        }
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority("ADMIN2"));
//        UserDetails userDetails = new org.springframework.security.core.userdetails.
//                User(user.getUsername(), user.getPassword(), authorities);
//
//        return userDetails;
//    }
    public Page<UserVO> getAllUser(int page, int size) {
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
    public Page<UserVO> getUserByAuthority(Integer authorityID, int page, int size, String sort) {
        String direction = sort.substring(0, 1);
        String keySort = sort.substring(1, sort.length());
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

    @Override
    public UserVO updateUserByAdmin(UserVO userVO) {
        User user = userRepository.findOne(userVO.getId());
        if (user != null) {
            user.setFullName(userVO.getFullName());
            user.setUsername(userVO.getUsername());
            user.setAddress(userVO.getAddress());
            user.setBirthday(userVO.getBirthday());
            user.setCreditCard(userVO.getCreditCard());
            user.setEmail(userVO.getEmail());
            user.setGender(userVO.getGender());
            user.setAvatar(userVO.getAvatar());
            Set<Authority> authorities = new HashSet<>();
            for (AuthorityVO authorityVO:userVO.getAuthorities()
                    ) {
                Authority authority = authorityRepository.findOne(authorityVO.getId());
                authorities.add(authority);
            }
            user.setAuthorities(authorities);
            return this.convertVO(userRepository.save(user));
        }
        return null;
    }

    @Override
    public UserVO updatePassword(int id, String newPassword) {
        User user = userRepository.findOne(id);
        if (user != null) {
            user.setPassword(newPassword);
            user = userRepository.save(user);
            return convertVO(user);
        }
        return null;
    }
    public UserVO getUserById(Integer id) {
        return convertVO(userRepository.findOne(id));
    }

    public UserVO convertVO(User user) {
        Set<AuthorityVO> authorityVOS = new HashSet<>();
        if (user.getAuthorities() != null) {
            for (Authority authority: user.getAuthorities()
                    ) {
                AuthorityVO authorityVO = new AuthorityVO();
                authorityVO.setId(authority.getId());
                authorityVO.setName(authority.getName());
                authorityVOS.add(authorityVO);
            }
        }
        List<OrderVO> orderVOS = new ArrayList<>();
        if (user.getOrders() != null) {
            for (Order order: user.getOrders()){
                orderVOS.add(orderService.convertVOWithoutOrderItem(order));
            }
        }
        UserVO userVO = UserVOBuilder.anUserVO().withId(user.getId())
                .withActive(user.getActive()).withAddress(user.getAddress())
                .withBirthday(user.getBirthday()).withAvatar(user.getAvatar())
                .withEmail(user.getEmail()).withCreditCard(user.getCreditCard())
                .withUsername(user.getUsername())
                .withGender(user.getGender())
                .withAuthorities(authorityVOS)
                .withFullName(user.getFullName())
                .withPhone(user.getPhone())
                .withOrder(orderVOS).build();
        return userVO;
    }

    @Override
    public UserVO delete(Integer id) {
        User user = userRepository.findOne(id);
        UserVO userVO = convertVO(user);
        userRepository.delete(user);
        return userVO;
    }
}
