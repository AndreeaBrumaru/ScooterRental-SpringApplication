package com.practice.scooterrentalspringapplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.scooterrentalspringapplication.dto.ScooterDto;
import com.practice.scooterrentalspringapplication.dto.UserDto;
import com.practice.scooterrentalspringapplication.dto.UserReportDto;
import com.practice.scooterrentalspringapplication.model.Report;
import com.practice.scooterrentalspringapplication.model.Scooter;
import com.practice.scooterrentalspringapplication.model.User;
import com.practice.scooterrentalspringapplication.model.enums.Condition;
import com.practice.scooterrentalspringapplication.service.interfaces.IUserService;
import org.junit.jupiter.api.Assertions;
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

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private IUserService userService;
    @MockBean
    private ModelMapper modelMapper;
    private Scooter s1;
    private Scooter s2;
    private Scooter s3;
    private Scooter s4;
    private UserDto u1;
    private UserDto u2;
    private User u3;
    private User u4;
    private User u5;
    private Report r1;
    private UserReportDto r2;
    private Report r3;
    private Report r4;


    @BeforeEach
    public void init()
    {
        s1 = new Scooter(1L, false, 42L, null, Condition.WORKING);
        s2 = new Scooter(2L, false, 42L, null, Condition.WORKING);
        s3 = new Scooter(3L, false, 42L, null, Condition.WORKING);
        s4 = new Scooter(4L, false, 42L, null, Condition.WORKING);

        u1 = new UserDto(5L, "test", "6111111111111", null);
        u2 = new UserDto(6L, "test", "6111111111112", null);
        u3 = new User(7L, "test", "6111111111113", null, true, false);
        u4 = new User(8L, "test", "6111111111114", null, false, false);
        u5 = new User(9L, "", "", null, false, false);

        r1 = new Report(s1, u3,  25L, 100L, true, LocalDate.now(), null);
        r2 = new UserReportDto(s1, 25L, 100L, true, LocalDate.now());
        r3 = new Report(s2, u4,  25L, 100L, false, LocalDate.now(), null);
    }

    @Test
    public void findUserReports() throws Exception
    {
        //GIVEN
        Mockito.when(userService.findReportsByUser(u3.getUserId())).thenReturn(Arrays.asList(r2));

        //WHEN
        mvc.perform(MockMvcRequestBuilders.get("/users/" + u3.getUserId() + "/reports")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void payRide() throws Exception
    {
        //GIVEN
        Mockito.doNothing().when(userService).payRide(u4.getUserId());

        //WHEN
        mvc.perform(MockMvcRequestBuilders.put("/users/" + u4.getUserId() + "/payRide")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findAllUsers() throws Exception
    {
        //GIVEN
        Mockito.when(userService.findAll()).thenReturn(Arrays.asList(u1, u2));

        //WHEN
        mvc.perform(MockMvcRequestBuilders.get("/admin/users/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void findAllUserDebts() throws Exception
    {
        //GIVEN
        Mockito.when(userService.findUsersDebts()).thenReturn(Arrays.asList(u1));

        //WHEN
        mvc.perform(MockMvcRequestBuilders.get("/admin/users/debts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void addPassed() throws Exception
    {
        //GIVEN
        Mockito.when(modelMapper.map(any(), any())).thenReturn(u3);
        Mockito.doNothing().when(userService).addUser(u3);

        //WHEN
        mvc.perform(MockMvcRequestBuilders.post("/admin/users")
                        .content(asJsonString(u3))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void addFailed() throws Exception
    {
        //GIVEN
        Mockito.when(modelMapper.map(any(), any())).thenReturn(u5);
        Mockito.doNothing().when(userService).addUser(u5);

        //WHEN
        mvc.perform(MockMvcRequestBuilders.post("/admin/users")
                        .content(asJsonString(u5))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void putRequest() throws Exception{
        //GIVEN
        Mockito.when(modelMapper.map(any(), any())).thenReturn(u4);
        Mockito.doNothing().when(userService).updateUser(9L, "updatedtest");

        //WHEN
        mvc.perform(MockMvcRequestBuilders.put("/admin/users/" + 9 + "?username=updatedtest")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteRequest() throws Exception{
        //GIVEN
        Mockito.doNothing().when(userService).deleteUser(u4.getUserId());

        //WHEN
        mvc.perform(MockMvcRequestBuilders.delete("/admin/users/" + u4.getUserId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    //Methods
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
