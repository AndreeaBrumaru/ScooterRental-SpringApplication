package com.practice.scooterrentalspringapplication.repository;

import com.practice.scooterrentalspringapplication.model.Report;
import com.practice.scooterrentalspringapplication.model.Scooter;
import com.practice.scooterrentalspringapplication.model.User;
import com.practice.scooterrentalspringapplication.model.enums.Condition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ReportRepositoryTest {
    @Autowired
    ReportRepository reportRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ScooterRepository scooterRepository;

    private Scooter s1, s2, s3, s4, s5, s6;
    private User u1, u2, u3, u4;
    private Report r1, r2, r3, r4;

    @BeforeEach
    public void init()
    {
        reportRepository.deleteAll();
        userRepository.deleteAll();
        scooterRepository.deleteAll();

        s1 = new Scooter(false, 100, null, Condition.WORKING);
        s2 = new Scooter(false, 80, null, Condition.WORKING);
        s3 = new Scooter(true, 45, 15L, Condition.WORKING);
        s4 = new Scooter(false, 10, null, Condition.NEEDS_CHARGING);
        s5 = new Scooter(false, 10, null, Condition.DECOMMISSIONED);
        s6 = new Scooter(false, 10, null, Condition.BROKEN);
        u1 = new User("test1", "6000000000000", s3, true, false);
        u2 = new User("test2", "6000000000000", null, true, false);
        u3 = new User("test3", "6000000000000", null, false, false);
        u4 = new User("test4", "6000000000000", null, false, true);
        r1 = new Report(s3, u2, 25L, 100L, true, LocalDate.of(2022, 12, 23), null);
        r2 = new Report(s2, u1, 25L, 100L, true, LocalDate.of(2022, 11, 23), null);
        r3 = new Report(s1, u3, 25L, 100L, false, LocalDate.of(2022, 10, 23), null);
        r4 = new Report(s4, u1, 25L, 100L, true, LocalDate.of(2022, 9, 23), null);

        scooterRepository.saveAll(Arrays.asList(s1, s2, s3, s4, s5, s6));
        userRepository.saveAll(Arrays.asList(u1, u2, u3, u4));
        reportRepository.saveAll(Arrays.asList(r1, r2, r3, r4));
    }

    @Test
    public void findReportByUser()
    {
        List<Report> reports = reportRepository.findReportByUser(u1);
        Assertions.assertEquals(Arrays.asList(r2, r4), reports);
    }

    @Test
    public void findReportByScooter()
    {
        List<Report> reports = reportRepository.findReportByScooter(s1);
        Assertions.assertEquals(Arrays.asList(r3), reports);
    }

    @Test
    public void findReportByDate()
    {
        List<Report> reports = reportRepository.findReportByDate(LocalDate.of(2022, 12, 23));
        Assertions.assertEquals(Arrays.asList(r1), reports);
    }

    @Test
    public void findReportsBetweenDates()
    {
        List<Report> reports = reportRepository.findByDateBetween(LocalDate.of(2022, 9, 01), LocalDate.of(2022, 11, 24));
        Assertions.assertEquals(Arrays.asList(r2, r3, r4), reports);
    }
}
