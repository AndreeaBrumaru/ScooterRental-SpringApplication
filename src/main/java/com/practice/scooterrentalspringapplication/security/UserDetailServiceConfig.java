package com.practice.scooterrentalspringapplication.security;

import com.practice.scooterrentalspringapplication.model.User;
import com.practice.scooterrentalspringapplication.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.List;

@Configuration
public class UserDetailServiceConfig {

    private final UserRepository userRepository;

    public UserDetailServiceConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public UserDetailsService userDetailsService(BCryptPasswordEncoder bCryptPasswordEncoder)
    {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

//        List<User> users = userRepository.findAll();
//        for(com.practice.scooterrentalspringapplication.model.User u :users)
//        {
//            manager.createUser(org.springframework.security.core.userdetails.User.withUsername(u.getUsername()).password(bCryptPasswordEncoder.encode(u.getPassword())).roles("USER").build());
//        }
        manager.createUser(org.springframework.security.core.userdetails.User.withUsername("user").password(bCryptPasswordEncoder.encode("password")).roles("USER").build());

        manager.createUser(org.springframework.security.core.userdetails.User.withUsername("admin").password(bCryptPasswordEncoder.encode("password")).roles("USER", "ADMIN").build());
        return manager;
    }

    //TODO Calluri http catre alte api-uri


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
