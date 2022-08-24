package com.practice.scooterrentalspringapplication.service;

import com.practice.scooterrentalspringapplication.dto.ReportDto;
import com.practice.scooterrentalspringapplication.exception.NoDataFoundException;
import com.practice.scooterrentalspringapplication.exception.ScooterNotFoundException;
import com.practice.scooterrentalspringapplication.exception.UserNotFoundException;
import com.practice.scooterrentalspringapplication.model.Report;
import com.practice.scooterrentalspringapplication.model.Scooter;
import com.practice.scooterrentalspringapplication.model.User;
import com.practice.scooterrentalspringapplication.repository.ReportRepository;
import com.practice.scooterrentalspringapplication.repository.ScooterRepository;
import com.practice.scooterrentalspringapplication.repository.UserRepository;
import com.practice.scooterrentalspringapplication.service.interfaces.IReportService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService implements IReportService {
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final ScooterRepository scooterRepository;
    private final ModelMapper modelMapper;

    //Constructor
    public ReportService(ReportRepository reportRepository, UserRepository userRepository, ScooterRepository scooterRepository, ModelMapper modelMapper) {
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
        this.scooterRepository = scooterRepository;
        this.modelMapper = modelMapper;
    }

    //Admin methods
    @Override
    public List<ReportDto> findAll() {
        List<Report> reports = reportRepository.findAll();
        if(reports.isEmpty())
        {
            throw new NoDataFoundException();
        }
        return reports.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<ReportDto> findReportByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        List<Report> reports = reportRepository.findReportByUser(user);
        if(reports.isEmpty())
        {
            throw new NoDataFoundException();
        }
        return reports.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<ReportDto> findReportByScooter(Long scooterId) {
        Scooter scooter = scooterRepository.findById(scooterId).orElseThrow(ScooterNotFoundException::new);
        List<Report> reports = reportRepository.findReportByScooter(scooter);
        if(reports.isEmpty())
        {
            throw new NoDataFoundException();
        }
        return reports.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<ReportDto> findReportByDate(LocalDate date) {
        List<Report> reports = reportRepository.findReportByDate(date);
        if(reports.isEmpty())
        {
            throw new NoDataFoundException();
        }
        return reports.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<ReportDto> findReportBetween(LocalDate startDate, LocalDate endDate) {
        List<Report> reports = reportRepository.findByDateBetween(startDate, endDate);
        if(reports.isEmpty())
        {
            throw new NoDataFoundException();
        }
        return reports.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    //Convert entity to dto
    private ReportDto convertToDto(Report report)
    {
        return modelMapper.map(report, ReportDto.class);
    }
}
