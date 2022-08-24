package com.practice.scooterrentalspringapplication.service;

import com.practice.scooterrentalspringapplication.dto.UserDto;
import com.practice.scooterrentalspringapplication.dto.UserReportDto;
import com.practice.scooterrentalspringapplication.exception.*;
import com.practice.scooterrentalspringapplication.model.Report;
import com.practice.scooterrentalspringapplication.model.Scooter;
import com.practice.scooterrentalspringapplication.model.User;
import com.practice.scooterrentalspringapplication.model.enums.Condition;
import com.practice.scooterrentalspringapplication.repository.ReportRepository;
import com.practice.scooterrentalspringapplication.repository.ScooterRepository;
import com.practice.scooterrentalspringapplication.repository.UserRepository;
import com.practice.scooterrentalspringapplication.service.interfaces.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final ScooterRepository scooterRepository;
    private final ReportRepository reportRepository;
    private final ModelMapper modelMapper;

    //Constructor
    public UserService(UserRepository userRepository, ScooterRepository scooterRepository, ReportRepository reportRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.scooterRepository = scooterRepository;
        this.reportRepository = reportRepository;
        this.modelMapper = modelMapper;
    }

    //User methods
    //find user orders
    @Override
    public List<UserReportDto> findReportsByUser(Long userId) {
        User user = findUserService(userId);
        List<Report> reports = userRepository.findUserOrders(user);
        if(reports.isEmpty())
        {
            throw new NoDataFoundException();
        }
        return reports.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public void payRide(Long userId) {
        User user = findUserService(userId);
        Report report = userRepository.findUnpaidOrder(user);
        if(!user.getPreviousRidePaid() && !report.getPaid())
        {
            user.setPreviousRidePaid(true);
            report.setPaid(true);
            userRepository.save(user);
            reportRepository.save(report);
        }
        else if(user.getPreviousRidePaid() && report.getPaid())
        {
            throw new RideAlreadyPaidException();
        }
        else
        {
            throw new WeirdErrorException();
        }
    }

    @Override
    public void startRide(Long scooterId, Long userId, Long timeRidden) {
        Scooter scooter = scooterRepository.findById(scooterId).orElseThrow(ScooterNotFoundException::new);
        User user = findUserService(userId);
        if(user.getScooter() != null || scooter.getInUse())
        {
            throw new RideInProgressException();
        }

        user.setScooter(scooter);

        scooter.setInUse(true);
        scooter.setTimeRidden(timeRidden);
        double usedBattery = (double)timeRidden / 3;
        scooter.setBattery(scooter.getBattery() - usedBattery);
        if(scooter.getBattery() <0)
        {
            throw new BatteryTooLowException();
        }

        userRepository.save(user);
        scooterRepository.save(scooter);
    }

    @Override
    public void endRide(Long userId, Boolean paid, String notes) {
        User user = findUserService(userId);
        Scooter scooter = user.getScooter();
        Report report = new Report();
        if(user.getScooter() == null)
        {
            throw new NoRideToProcessException();
        }

        report.setScooter(scooter);
        report.setUser(user);
        report.setMinutesRidden(scooter.getTimeRidden());
        report.setPrice(4* report.getMinutesRidden());
        report.setPaid(paid);
        report.setDate(LocalDate.now());
        report.setNotes(notes);

        user.setScooter(null);
        user.setPreviousRidePaid(paid);

        scooter.setInUse(false);
        scooter.setTimeRidden(null);
        if(scooter.getBattery() < 15)
        {
            scooter.setCondition(Condition.NEEDS_CHARGING);
        }

        reportRepository.save(report);
        userRepository.save(user);
        scooterRepository.save(scooter);
    }

    //Admin methods
    @Override
    public List<UserDto> findUsersDebts() {
        List<User> users = userRepository.findUserDebts();
        if(users.isEmpty())
        {
            throw new NoDataFoundException();
        }
        return users.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();
        if(users.isEmpty())
        {
            throw new NoDataFoundException();
        }
        return users.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Long userId) {
        User user = findUserService(userId);
        return convertToDto(user);
    }

    @Override
    public void addUser(User user) {
        user.setDeleted(false);
        user.setScooter(null);
        user.setPreviousRidePaid(true);
        userRepository.save(user);
    }

    @Override
    public void updateUser(Long userId, String username) {
        User user = findUserService(userId);
        user.setUsername(username);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = findUserService(userId);
        user.setDeleted(true);
        userRepository.save(user);
    }

    //Convert entity to Dto
    private UserDto convertToDto(User user)
    {
        UserDto userdto = new UserDto();
        userdto.setUserId(user.getUserId());
        userdto.setUsername(user.getUsername());
        userdto.setCNP(user.getCNP());
        userdto.setScooter(user.getScooter());
        return userdto;
    }

    private UserReportDto convertToDto(Report report)
    {
        UserReportDto reportDto = new UserReportDto();
        reportDto.setReportId(report.getReportId());
        reportDto.setMinutesRidden(report.getMinutesRidden());
        reportDto.setPaid(report.getPaid());
        reportDto.setPrice(report.getPrice());
        reportDto.setScooter(report.getScooter());
        reportDto.setDate(report.getDate());
        return reportDto;
    }

    //Find user, used only by UserService
    private User findUserService(Long userId)
    {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }
}
