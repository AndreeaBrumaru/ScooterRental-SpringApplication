package com.practice.scooterrentalspringapplication.controller;

import com.practice.scooterrentalspringapplication.dto.ScooterDto;
import com.practice.scooterrentalspringapplication.model.Scooter;
import com.practice.scooterrentalspringapplication.model.enums.Condition;
import com.practice.scooterrentalspringapplication.service.interfaces.IScooterService;
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
import java.util.Arrays;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ScooterController.class)
public class ScooterControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private IScooterService scooterService;
    @MockBean
    private ModelMapper modelMapper;

    private ScooterDto s1, s2, s5, s6, s7;
    private Scooter s3, s4;

    @BeforeEach
    public void init()
    {
        s1 = new ScooterDto(1L, true, 45L, 12L, Condition.WORKING);
        s2 = new ScooterDto(2L, false, 42L, null, Condition.WORKING);
        s5 = new ScooterDto(5L, false, 10L, null, Condition.NEEDS_CHARGING);
        s6 = new ScooterDto(6L, false, 42L, null, Condition.BROKEN);
        s7 = new ScooterDto(7L, false, 42L, null, Condition.DECOMMISSIONED);
        s3 = new Scooter(3L, false, 42L, null, Condition.WORKING);
        s4 = new Scooter(4L, false, 42L, null, Condition.WORKING);
    }

    @Test
    public void findAvailableScooters() throws Exception
    {
        //GIVEN
        Mockito.when(scooterService.findAvailableScooters()).thenReturn(Arrays.asList(s2));

        //WHEN
        mvc.perform(MockMvcRequestBuilders.get("/scooters/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void findAllScooters() throws Exception
    {
        //GIVEN
        Mockito.when(scooterService.findAllScooters()).thenReturn(Arrays.asList(s1, s2, s6, s7, s5));

        //WHEN
        mvc.perform(MockMvcRequestBuilders.get("/admin/scooters/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    public void findAllScootersThatNeedCharging() throws Exception
    {
        //GIVEN
        Mockito.when(scooterService.findScootersThatNeedCharging()).thenReturn(Arrays.asList(s5));

        //WHEN
        mvc.perform(MockMvcRequestBuilders.get("/admin/scooters/needCharging")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void findAllBrokenScooters() throws Exception
    {
        //GIVEN
        Mockito.when(scooterService.findBrokenScooters()).thenReturn(Arrays.asList(s6));

        //WHEN
        mvc.perform(MockMvcRequestBuilders.get("/admin/scooters/broken")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void findAllDecommissionedScooters() throws Exception
    {
        //GIVEN
        Mockito.when(scooterService.findDecommissionedScooters()).thenReturn(Arrays.asList(s6));

        //WHEN
        mvc.perform(MockMvcRequestBuilders.get("/admin/scooters/decommissioned")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void findById() throws Exception
    {
        //GIVEN
        Mockito.when(scooterService.findScooterById(any())).thenReturn(s1);

        //WHEN
        mvc.perform(MockMvcRequestBuilders.get("/admin/scooters/" + 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void add() throws Exception
    {
        //GIVEN
        Mockito.doNothing().when(scooterService).addScooter();

        //WHEN
        mvc.perform(MockMvcRequestBuilders.post("/admin/scooters")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void charge() throws Exception
    {
        //GIVEN
        Mockito.doNothing().when(scooterService).chargeAllScooters();

        //WHEN
        mvc.perform(MockMvcRequestBuilders.put("/admin/scooters/charge")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void setBroken() throws Exception
    {

        //GIVEN
        Mockito.doNothing().when(scooterService).setBrokenScooter(s4.getScooterId());

        //WHEN
        mvc.perform(MockMvcRequestBuilders.put("/admin/scooters/" + s4.getScooterId() + "/broken")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteRequest() throws Exception{
        //GIVEN
        Mockito.doNothing().when(scooterService).deleteScooter(s4.getScooterId());

        //WHEN
        mvc.perform(MockMvcRequestBuilders.delete("/admin/scooters/" + s4.getScooterId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
