package com.practice.scooterrentalspringapplication.controller;

import com.practice.scooterrentalspringapplication.dto.ReportDto;
import com.practice.scooterrentalspringapplication.service.interfaces.IReportService;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.util.List;

@RestController
public class ReportController {
    private final IReportService reportService;
    private final ModelMapper modelMapper;

    //Constructor
    public ReportController(IReportService reportService, ModelMapper modelMapper) {
        this.reportService = reportService;
        this.modelMapper = modelMapper;
    }

    //Admin methods
    @GetMapping("/admin/reports")
    public List<ReportDto> findAllReports()
    {
        return reportService.findAll();
    }

    @GetMapping("/admin/reports/user/{userId}")
    public List<ReportDto> findReportsByUser(@PathVariable Long userId)
    {
        return reportService.findReportByUser(userId);
    }

    @GetMapping("/admin/reports/scooter/{scooterId}")
    public List<ReportDto> findReportByScooter(@PathVariable Long scooterId)
    {
        return reportService.findReportByScooter(scooterId);
    }

    @GetMapping("/admin/reports/date")
    public List<ReportDto> findReportByDate(@RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate date)
    {
        return reportService.findReportByDate(date);
    }

    @GetMapping("/admin/reports/between")
    public List<ReportDto> findReportByDate(@RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate startDate, @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate endDate)
    {
        return reportService.findReportBetween(startDate, endDate);
    }
}
