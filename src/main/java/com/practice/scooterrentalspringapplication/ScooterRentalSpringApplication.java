package com.practice.scooterrentalspringapplication;

import com.practice.scooterrentalspringapplication.security.SecurityConfiguration;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@SpringBootApplication
public class ScooterRentalSpringApplication {

    @Bean
    public ModelMapper modelMapper()
    {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(ScooterRentalSpringApplication.class, args);
    }
}
