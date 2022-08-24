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
            //TODO Replace exception(no data found)
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
            //TODO Replace exception(no data found)
            throw new RuntimeException();
        }
        return scooters.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<ScooterDto> findScootersThatNeedCharging() {
        List<Scooter> scooters = scooterRepository.findScootersThatNeedCharging();
        if(scooters.isEmpty())
        {
            //TODO Replace exception (no data found)
            throw new RuntimeException();
        }
        return scooters.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<ScooterDto> findBrokenScooters() {
        List<Scooter> scooters = scooterRepository.findBrokenScooters();
        if(scooters.isEmpty())
        {
            //TODO Replace exception (no data found)
            throw new RuntimeException();
        }
        return scooters.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<ScooterDto> findDecommissionedScooters() {
        List<Scooter> scooters = scooterRepository.findDecommisionedScooters();
        if(scooters.isEmpty())
        {
            //TODO Replace exception (no data found)
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
    public void deleteScooter(Long scooterId) {
        Scooter scooter = findScooterService(scooterId);
        scooter.setCondition(Condition.DECOMMISSIONED);
        scooterRepository.save(scooter);
    }

    @Override
    public void chargeAllScooters() {
        List<Scooter> scooters = scooterRepository.findScootersThatNeedCharging();
        if(scooters.isEmpty())
        {
            //TODO Replace exception (no data found)
            throw new RuntimeException();
        }
        for(Scooter s : scooters)
        {
            s.setBattery(100);
            s.setCondition(Condition.WORKING);
            scooterRepository.save(s);
        }
    }

    @Override
    public void setBrokenScooter(Long scooterId) {
        Scooter scooter = findScooterService(scooterId);
        if(scooter.getCondition() == Condition.DECOMMISSIONED)
        {
            //TODO Replace exception (no scooter found)
            throw new RuntimeException();
        }
        if(scooter.getCondition() == Condition.BROKEN)
        {
            //TODO Replace exception (scooter already broken)
            throw new RuntimeException();
        }
        scooter.setCondition(Condition.BROKEN);
        scooterRepository.save(scooter);
    }

    @Override
    public void fixBrokenScooter (Long scooterId) {
        Scooter scooter = findScooterService(scooterId);
        if(scooter.getCondition() == Condition.DECOMMISSIONED)
        {
            //TODO Replace exception (no scooter found)
            throw new RuntimeException();
        }
        if(scooter.getCondition() == Condition.WORKING || scooter.getCondition() == Condition.NEEDS_CHARGING)
        {
            //TODO Replace exception (scooter doesn't need to be fixed)
            throw new RuntimeException();
        }
        scooter.setCondition(Condition.WORKING);
        scooter.setBattery(100);
        scooterRepository.save(scooter);
    }

    //Convert entity to Dto
    private ScooterDto convertToDto(Scooter scooter)
    {
        return modelMapper.map(scooter, ScooterDto.class);
    }

    //Find scooter, used only by ScooterService
    private Scooter findScooterService(Long scooterId)
    {
        //TODO Replace exception (no scooter found)
        return scooterRepository.findById(scooterId).orElseThrow(RuntimeException::new);
    }
}
