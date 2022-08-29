package com.practice.scooterrentalspringapplication.service.interfaces;

import com.practice.scooterrentalspringapplication.dto.ReportDto;
import java.time.LocalDate;
import java.util.List;

public interface IReportService {
    //Admin methods
    List<ReportDto> findAll();
    List<ReportDto> findReportByUser(Long userId);
    List<ReportDto> findReportByScooter(Long scooterId);
    List<ReportDto> findReportByDate(LocalDate date);
    List<ReportDto> findReportBetween(LocalDate startDate, LocalDate endDate);
}
