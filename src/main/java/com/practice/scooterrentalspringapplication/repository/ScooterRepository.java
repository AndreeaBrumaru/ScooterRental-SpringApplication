package com.practice.scooterrentalspringapplication.repository;

import com.practice.scooterrentalspringapplication.model.Scooter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public interface ScooterRepository extends JpaRepository<Scooter, Long> {

    //User methods
    @Query("SELECT s FROM Scooter s WHERE s.inUse = false AND s.condition = 0")
    List<Scooter> findAvailableScooters();

    //Admin methods
    @Query("SELECT s FROM Scooter s WHERE NOT s.condition = 3")
    List<Scooter> findAllScooters();

    @Query("SELECT s FROM Scooter s WHERE s.condition = 1")
    List<Scooter> findScootersThatNeedCharging();

    @Query("SELECT s FROM Scooter s WHERE s.condition = 2")
    List<Scooter> findBrokenScooters();

    @Query("SELECT s FROM Scooter s WHERE s.condition = 3")
    List<Scooter> findDecommisionedScooters();
}
