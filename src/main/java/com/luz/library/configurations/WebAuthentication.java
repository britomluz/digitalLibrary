package com.luz.library.configurations;

import com.luz.library.models.User;
import com.luz.library.models.UserRole;
import com.luz.library.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebAuthentication extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    UserServiceImpl userService;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(inputName-> {

            User user = userService.getByEmail(inputName);

            if (user != null) {
                if(user.getRole().equals(UserRole.ADMIN)){
                    return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),  AuthorityUtils.createAuthorityList("ADMIN"));
                } else {
                    return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),  AuthorityUtils.createAuthorityList("ADMIN"));
                }
            }
            else {
                throw new UsernameNotFoundException("Unknown user: " + inputName);
            }


        });

    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}