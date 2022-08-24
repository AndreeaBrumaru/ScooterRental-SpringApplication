package com.practice.scooterrentalspringapplication.repository;

import com.practice.scooterrentalspringapplication.model.Report;
import com.practice.scooterrentalspringapplication.model.Scooter;
import com.practice.scooterrentalspringapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    @Query("SELECT r FROM Report r WHERE r.user = :user")
    List<Report> findReportByUser(User user);

    @Query("SELECT r FROM Report r WHERE r.scooter = :scooter")
    List<Report> findReportByScooter(Scooter scooter);

    @Query("SELECT r FROM Report r WHERE r.date = :date")
    List<Report> findReportByDate(LocalDate date);
    List<Report> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
