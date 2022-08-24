package com.practice.scooterrentalspringapplication.service.interfaces;

import com.practice.scooterrentalspringapplication.dto.ReportDto;
import com.practice.scooterrentalspringapplication.dto.UserDto;
import com.practice.scooterrentalspringapplication.dto.UserReportDto;
import com.practice.scooterrentalspringapplication.model.User;
import java.util.List;

public interface IUserService {
    //User methods
    List<UserReportDto> findReportsByUser(Long userId);
    void payRide(Long userId);
    void startRide(Long scooterId, Long userId, Long timeRidden);
    void endRide(Long userId, Boolean paid, String notes);
    //Admin methods
    List<UserDto> findUsersDebts();
    List<UserDto> findAll();
    UserDto findById(Long userId);
    void addUser(User user);
    void updateUser(Long userId, String username);
    void deleteUser(Long userId);
}
