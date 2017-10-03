package com.aptech.foodmarket.food_market.security.service;

import com.aptech.foodmarket.food_market.model.User;
import com.aptech.foodmarket.food_market.repository.UserRepository;
import com.aptech.foodmarket.food_market.service.ImplService.UserServiceImpl;
import com.aptech.foodmarket.food_market.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service(value = "customAuth")
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    public UserRepository storage;
    @Autowired
    public UserServiceImpl userService;

    @Override
    @Transactional
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();
        User user = storage.findByEmail(login);
        if (user == null) {
            return null;
        } else {
            if (userService.getPasswordEncoder().matches(password,user.getPassword())){
                // Here use the user object to only check if the user exists in the database if not null use his login ( principal ) and password
                return new UsernamePasswordAuthenticationToken(user.getUsername(), password);
            }
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
