package com.practice.scooterrentalspringapplication.repository;

import com.practice.scooterrentalspringapplication.model.Scooter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.practice.scooterrentalspringapplication.model.enums.Condition;
import java.util.List;

public interface ScooterRepository extends JpaRepository<Scooter, Long> {

    //User methods
    //TODO See if this actually works
    @Query("SELECT s FROM Scooter s WHERE s.inUse = false AND s.condition = 0")
    List<Scooter> findAvailableScooters();

    //Admin methods
    @Query("SELECT s FROM Scooter s WHERE s.inUse = true")
    List<Scooter> findUsedScooters();

    //TODO See if this actually works
    @Query("SELECT s FROM Scooter s WHERE s.condition = 1")
    List<Scooter> findScootersThatNeedCharging();

    //TODO See if this actually works
    @Query("SELECT s FROM Scooter s WHERE s.condition = 2")
    List<Scooter> findBrokenScooters();

    //TODO See if this actually works
    @Query("SELECT s FROM Scooter s WHERE s.condition = 3")
    List<Scooter> findDecommisionedScooters();
}
