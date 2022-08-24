package com.practice.scooterrentalspringapplication.service.interfaces;

import com.practice.scooterrentalspringapplication.dto.ScooterDto;
import com.practice.scooterrentalspringapplication.model.Scooter;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IScooterService {

    //User methods
    List<ScooterDto> findAvailableScooters();

    //Admin methods
    List<ScooterDto> findAllScooters();
    List<ScooterDto> findScootersThatNeedCharging();
    List<ScooterDto> findBrokenScooters();
    List<ScooterDto> findDecommissionedScooters();
    ScooterDto findScooterById(Long scooterId);
    void addScooter();
    void updateScooter(Long scooterId, Scooter updatedInfo);
    void deleteScooter(Long scooterId);
    void chargeAllScooters();
}
