package com.practice.scooterrentalspringapplication.controller;

import com.practice.scooterrentalspringapplication.dto.ScooterDto;
import com.practice.scooterrentalspringapplication.model.Scooter;
import com.practice.scooterrentalspringapplication.service.interfaces.IScooterService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ScooterController {
    private final IScooterService scooterService;
    private final ModelMapper modelMapper;

    //Constructor
    public ScooterController(IScooterService scooterService, ModelMapper modelMapper) {
        this.scooterService = scooterService;
        this.modelMapper = modelMapper;
    }

    //User methods
    @GetMapping("/scooters")
    public List<ScooterDto> findAvailableScooters()
    {
        return scooterService.findAvailableScooters();
    }

    //Admin methods
    @GetMapping("/admin/scooters/available")
    public List<ScooterDto> findAllAvailable()
    {
        return scooterService.findAvailableScooters();
    }

    @GetMapping("/admin/scooters")
    public List<ScooterDto> findAllScooters()
    {
        return scooterService.findAllScooters();
    }

    @GetMapping("/admin/scooters/needCharging")
    public List<ScooterDto> findAllScootersThatNeedCharging()
    {
        return scooterService.findScootersThatNeedCharging();
    }

    @GetMapping("/admin/scooters/broken")
    public List<ScooterDto> findAllBrokenScooters()
    {
        return scooterService.findBrokenScooters();
    }

    @GetMapping("/admin/scooters/decommissioned")
    public List<ScooterDto> findAllDecommissionedScooters()
    {
        return scooterService.findDecommissionedScooters();
    }

    @GetMapping("/admin/scooters/{scooterId}")
    public ScooterDto findScooterById(@PathVariable Long scooterId)
    {
        return scooterService.findScooterById(scooterId);
    }

    @PostMapping("/admin/scooters")
    public ResponseEntity<String> addNewScooter()
    {
        scooterService.addScooter();
        return ResponseEntity.ok("New scooter added.");
    }

    @PutMapping("/admin/scooters/charge")
    public ResponseEntity<String> chargeScooters()
    {
        scooterService.chargeAllScooters();
        return ResponseEntity.ok("All scooters charged.");
    }

    @PutMapping("/admin/scooters/{scooterId}/broken")
    public ResponseEntity<String> setBrokenScooter(@PathVariable Long scooterId)
    {
        scooterService.setBrokenScooter(scooterId);
        return ResponseEntity.ok("The scooter has been sent to the warehouse due to it being broken.");
    }

    @PutMapping("/admin/scooters/{scooterId}/fix")
    public ResponseEntity<String> fixBrokenScooter(@PathVariable Long scooterId)
    {
        scooterService.fixBrokenScooter(scooterId);
        return ResponseEntity.ok("The scooter has been fixed and is back in circulation.");
    }

    @DeleteMapping("/admin/scooters/{scooterId}")
    public ResponseEntity<String> deleteScooter(@PathVariable Long scooterId)
    {
        scooterService.deleteScooter(scooterId);
        return ResponseEntity.ok("Scooter deleted.");
    }

    //Convert Dto to entity
    private Scooter convertToEntity(ScooterDto scooterDto)
    {
        return modelMapper.map(scooterDto, Scooter.class);
    }
}
