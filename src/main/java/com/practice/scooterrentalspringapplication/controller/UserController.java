package com.practice.scooterrentalspringapplication.controller;

import com.practice.scooterrentalspringapplication.dto.UserDto;
import com.practice.scooterrentalspringapplication.dto.UserReportDto;
import com.practice.scooterrentalspringapplication.model.Report;
import com.practice.scooterrentalspringapplication.model.User;
import com.practice.scooterrentalspringapplication.service.interfaces.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
public class UserController {
    private final IUserService userService;
    private final ModelMapper modelMapper;

    //Constructor
    public UserController(IUserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    //User methods
    @GetMapping("/users/{userId}/reports")
    public List<UserReportDto> findReportsOfUser(@PathVariable Long userId)
    {
        return userService.findReportsByUser(userId);
    }

    @PutMapping("/users/{userId}/payRide")
    public ResponseEntity<String> payPreviousUnpaidRide(@PathVariable Long userId)
    {
        userService.payRide(userId);
        return ResponseEntity.ok("Previous unpaid ride paid successfully.");
    }

    @PostMapping("/users/{userId}/{scooterId}/{minutesRidden}/start")
    public ResponseEntity<String> startRide(@PathVariable Long userId, @PathVariable Long scooterId, @PathVariable Long minutesRidden)
    {
        userService.startRide(scooterId, userId, minutesRidden);
        return ResponseEntity.ok("Ride started.");
    }

    @PutMapping("/users/{userId}/end")
    public ResponseEntity<String> endRide(@PathVariable Long userId, @RequestParam Boolean paid, @RequestParam(required = false) String notes)
    {
        userService.endRide(userId, paid, notes);
        return ResponseEntity.ok("Ride ended.");
    }

    //Admin methods
    @GetMapping("/admin/users")
    public List<UserDto> findAllUsers()
    {
        return userService.findAll();
    }

    @GetMapping("/admin/users/debts")
    public List<UserDto> findUserDebts()
    {
        return userService.findUsersDebts();
    }

    @GetMapping("/admin/users/{userId}")
    public UserDto findUserById(@PathVariable Long userId)
    {
        return userService.findById(userId);
    }

    @PostMapping("/admin/users")
    public ResponseEntity<String> addUser(@Valid @RequestBody UserDto userDto)
    {
        User user = convertToEntity(userDto);
        userService.addUser(user);
        return ResponseEntity.ok("User added.");
    }

    @PutMapping("/admin/users/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable Long userId, @RequestParam String username)
    {
        userService.updateUser(userId, username);
        return ResponseEntity.ok("User updated.");
    }

    @DeleteMapping("/admin/users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId)
    {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted.");
    }

    //Convert Dto to entity
    private User convertToEntity(UserDto userDto)
    {
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setUsername(userDto.getUsername());
        user.setCNP(userDto.getCNP());
        user.setScooter(userDto.getScooter());
        return user;
    }
}
