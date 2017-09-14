package com.aptech.foodmarket.food_market.service.ImplService;

import com.aptech.foodmarket.food_market.builder.UserVOBuilder;
import com.aptech.foodmarket.food_market.model.Authority;
import com.aptech.foodmarket.food_market.model.User;
import com.aptech.foodmarket.food_market.repository.UserRepository;
import com.aptech.foodmarket.food_market.security.JwtTokenUtil;
import com.aptech.foodmarket.food_market.security.JwtUser;
import com.aptech.foodmarket.food_market.service.UserService;
import com.aptech.foodmarket.food_market.vo.UserVO;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
public class UserServiceImpl implements UserService {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public User save(User user) {
        user.setPassword(getPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
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
        user.setAvatar(userVO.getAvatar());
        user.setActive(true);
        List<Authority> list = new ArrayList<>();
        user.setAuthorities(list);
        return this.save(user);

    }

    @Override
    public UserVO update(String token, UserVO userVO) {
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if ( username!= null) {
            // JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
            User user = userRepository.findByUsername(username);
            if (user != null ) {
                user.setFullName(userVO.getFullName());
                user.setAddress(userVO.getAddress());
                user.setBirthday(userVO.getBirthday());
                user.setEmail(userVO.getEmail());
                user.setGender(userVO.getGender());
                user.setAvatar(userVO.getAvatar());
                return this.convertVO(userRepository.save(user));
            }
        }
        return null;
    }

    @Override
    public UserVO getDetailUser(String user_token) {
        String username = jwtTokenUtil.getUsernameFromToken(user_token);
        if ( username!= null) {
            User user = userRepository.findByUsername(username);
            return this.convertVO(user);
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
