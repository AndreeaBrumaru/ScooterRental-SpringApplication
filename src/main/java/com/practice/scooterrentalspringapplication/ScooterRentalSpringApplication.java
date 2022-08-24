package com.practice.scooterrentalspringapplication;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
