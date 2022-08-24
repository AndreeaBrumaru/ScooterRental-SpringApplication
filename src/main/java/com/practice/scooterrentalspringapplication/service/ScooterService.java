package com.practice.scooterrentalspringapplication.service;

import com.practice.scooterrentalspringapplication.dto.ScooterDto;
import com.practice.scooterrentalspringapplication.model.Scooter;
import com.practice.scooterrentalspringapplication.model.enums.Condition;
import com.practice.scooterrentalspringapplication.repository.ScooterRepository;
import com.practice.scooterrentalspringapplication.service.interfaces.IScooterService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScooterService implements IScooterService {

    private final ScooterRepository scooterRepository;
    private final ModelMapper modelMapper;

    //Constructor
    public ScooterService(ScooterRepository scooterRepository, ModelMapper modelMapper) {
        this.scooterRepository = scooterRepository;
        this.modelMapper = modelMapper;
    }

    //User methods
    //Find all available scooters
    @Override
    public List<ScooterDto> findAvailableScooters() {
        List<Scooter> scooters = scooterRepository.findAvailableScooters();
        if(scooters.isEmpty())
        {
            //TODO Replace exception
            throw new RuntimeException();
        }
        return scooters.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    //Admin methods
    @Override
    public List<ScooterDto> findAllScooters()
    {
        List<Scooter> scooters = scooterRepository.findAll();
        if(scooters.isEmpty())
        {
            //TODO Replace exception
            throw new RuntimeException();
        }
        return scooters.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<ScooterDto> findScootersThatNeedCharging() {
        List<Scooter> scooters = scooterRepository.findScootersThatNeedCharging();
        if(scooters.isEmpty())
        {
            //TODO Replace exception
            throw new RuntimeException();
        }
        return scooters.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<ScooterDto> findBrokenScooters() {
        List<Scooter> scooters = scooterRepository.findBrokenScooters();
        if(scooters.isEmpty())
        {
            //TODO Replace exception
            throw new RuntimeException();
        }
        return scooters.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<ScooterDto> findDecommissionedScooters() {
        List<Scooter> scooters = scooterRepository.findDecommisionedScooters();
        if(scooters.isEmpty())
        {
            //TODO Replace exception
            throw new RuntimeException();
        }
        return scooters.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public ScooterDto findScooterById(Long scooterId) {
        Scooter scooter = findScooterService(scooterId);
        return convertToDto(scooter);
    }

    @Override
    public void addScooter() {
        Scooter scooter = new Scooter();
        scooter.setInUse(false);
        scooter.setBattery(100);
        scooter.setCondition(Condition.WORKING);
        scooterRepository.save(scooter);
    }

    @Override
    public void updateScooter(Long scooterId, Scooter updatedInfo) {
        //TODO Test if null when not written in something
        Scooter scooter = findScooterService(scooterId);
        scooter.setInUse(updatedInfo.getInUse());
        scooter.setBattery(updatedInfo.getBattery());
        scooter.setTimeRidden(updatedInfo.getTimeRidden());
        scooter.setCondition(updatedInfo.getCondition());
        scooterRepository.save(scooter);
    }

    @Override
    public void deleteScooter(Long scooterId) {
        //TODO Check what soft delete means
        Scooter scooter = findScooterService(scooterId);
        scooter.setCondition(Condition.DECOMMISIONED);
        scooterRepository.save(scooter);
    }

    @Override
    public void chargeAllScooters() {
        List<Scooter> scooters = scooterRepository.findScootersThatNeedCharging();
        for(Scooter s : scooters)
        {
            s.setBattery(100);
            scooterRepository.save(s);
        }
    }

    //Convert entity to Dto
    private ScooterDto convertToDto(Scooter scooter)
    {
        return modelMapper.map(scooter, ScooterDto.class);
    }

    //Find scooter, used only by ScooterService
    private Scooter findScooterService(Long scooterId)
    {
        //TODO Replace exception
        return scooterRepository.findById(scooterId).orElseThrow(RuntimeException::new);
    }
}
