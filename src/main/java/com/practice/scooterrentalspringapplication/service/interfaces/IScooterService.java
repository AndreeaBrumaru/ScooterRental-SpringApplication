package com.practice.scooterrentalspringapplication.service.interfaces;

import com.practice.scooterrentalspringapplication.dto.ScooterDto;
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
    void deleteScooter(Long scooterId);
    void chargeAllScooters();
    void setBrokenScooter(Long scooterId);
    void fixBrokenScooter(Long scooterId);
}
