package com.practice.scooterrentalspringapplication.controller;

import com.practice.scooterrentalspringapplication.dto.ReportDto;
import com.practice.scooterrentalspringapplication.dto.UserDto;
import com.practice.scooterrentalspringapplication.dto.UserReportDto;
import com.practice.scooterrentalspringapplication.model.Report;
import com.practice.scooterrentalspringapplication.model.Scooter;
import com.practice.scooterrentalspringapplication.model.User;
import com.practice.scooterrentalspringapplication.model.enums.Condition;
import com.practice.scooterrentalspringapplication.service.interfaces.IReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.JsonPathResultMatchers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReportController.class)
public class ReportControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private IReportService reportService;
    @MockBean
    private ModelMapper modelMapper;

    private Scooter s1, s2, s3, s4;
    private User u1, u2, u3, u4;
    private ReportDto r1, r2;
    private Report r3, r4, r5, r6;

    @BeforeEach
    public void init()
    {
        s1 = new Scooter(1L, false, 42L, null, Condition.WORKING);
        s2 = new Scooter(2L, false, 42L, null, Condition.WORKING);
        s3 = new Scooter(3L, false, 42L, null, Condition.WORKING);
        s4 = new Scooter(4L, false, 42L, null, Condition.WORKING);

        u1 = new User(7L, "test", "6111111111113", null, true, false);
        u2 = new User(8L, "test", "6111111111114", null, false, false);
        u3 = new User(9L, "e", "e", null, false, false);
        u4 = new User(10L, "e", "e", null, false, false);

        r1 = new ReportDto(s1, u3,  25L, 100L, true, LocalDate.of(2022, 01,01), null);
        r2 = new ReportDto(s1, u3,  25L, 100L, true, LocalDate.of(2022, 04, 01), null);
        r3 = new Report(s2, u4,  25L, 100L, false, LocalDate.now(), null);
        r4 = new Report(s2, u4,  25L, 100L, false, LocalDate.now(), null);
        r5 = new Report(s2, u4,  25L, 100L, false, LocalDate.now(), null);
        r6 = new Report(s2, u4,  25L, 100L, false, LocalDate.now(), null);
    }

    @Test
    public void findAllReports() throws Exception
    {
        //GIVEN
        Mockito.when(reportService.findAll()).thenReturn(Arrays.asList(r1, r2));

        //WHEN
        mvc.perform(MockMvcRequestBuilders.get("/admin/reports")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void findReportsByUser() throws Exception
    {
        //GIVEN
        Mockito.when(reportService.findReportByUser(any())).thenReturn(Arrays.asList(r1, r2));

        //WHEN
        mvc.perform(MockMvcRequestBuilders.get("/admin/reports/user/" + u3.getUserId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void findReportsByScooters() throws Exception
    {
        //GIVEN
        Mockito.when(reportService.findReportByScooter(any())).thenReturn(Arrays.asList(r1, r2));

        //WHEN
        mvc.perform(MockMvcRequestBuilders.get("/admin/reports/scooter/" + s1.getScooterId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void findReportsByDate() throws Exception
    {
        //GIVEN
        Mockito.when(reportService.findReportByDate(any())).thenReturn(Arrays.asList(r1));

        //WHEN
        mvc.perform(MockMvcRequestBuilders.get("/admin/reports/date?date=01.01.2022")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void findReportsByBetweenDate() throws Exception
    {
        //GIVEN
        Mockito.when(reportService.findReportBetween(any(), any())).thenReturn(Arrays.asList(r1, r2));

        //WHEN
        mvc.perform(MockMvcRequestBuilders.get("/admin/reports/between?startDate=01.01.2021&endDate=10.10.2022")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)));
    }
}
