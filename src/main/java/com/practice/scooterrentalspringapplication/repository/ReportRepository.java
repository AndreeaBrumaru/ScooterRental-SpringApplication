package com.practice.scooterrentalspringapplication.repository;

import com.practice.scooterrentalspringapplication.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
