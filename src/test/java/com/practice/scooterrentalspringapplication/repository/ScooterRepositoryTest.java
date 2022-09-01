package com.practice.scooterrentalspringapplication.repository;

import com.practice.scooterrentalspringapplication.model.Scooter;
import com.practice.scooterrentalspringapplication.model.enums.Condition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ScooterRepositoryTest {
    @Autowired
    ScooterRepository scooterRepository;

    private Scooter s1, s2, s3, s4, s5, s6;

    @BeforeEach
    public void init()
    {
        s1 = new Scooter(false, 100, null, Condition.WORKING);
        s2 = new Scooter(false, 80, null, Condition.WORKING);
        s3 = new Scooter(true, 45, 15L, Condition.WORKING);
        s4 = new Scooter(false, 10, null, Condition.NEEDS_CHARGING);
        s5 = new Scooter(false, 10, null, Condition.DECOMMISSIONED);
        s6 = new Scooter(false, 10, null, Condition.BROKEN);

        scooterRepository.save(s1);
        scooterRepository.save(s2);
        scooterRepository.save(s3);
        scooterRepository.save(s4);
        scooterRepository.save(s5);
        scooterRepository.save(s6);
    }

    @Test
    public void findAllScooters()
    {
        List<Scooter> scooters = scooterRepository.findAllScooters();
        Assertions.assertEquals(Arrays.asList(s1, s2, s3, s4, s6), scooters);
    }

    @Test
    public void findScootersThatNeedCharging()
    {
        List<Scooter> scooters = scooterRepository.findScootersThatNeedCharging();
        Assertions.assertNotNull(scooters);
        for (Scooter s :scooters) {
            Assertions.assertEquals(Condition.NEEDS_CHARGING, s.getCondition());
        }
    }

    @Test
    public void findBrokenScooters()
    {
        List<Scooter> scooters = scooterRepository.findBrokenScooters();
        Assertions.assertNotNull(scooters);
        for(Scooter s : scooters)
        {
            Assertions.assertEquals(Condition.BROKEN, s.getCondition());
        }
    }

    @Test
    public void findDecommissionedScooters()
    {
        List<Scooter> scooters = scooterRepository.findDecommisionedScooters();
        Assertions.assertNotNull(scooters);
        for(Scooter s : scooters)
        {
            Assertions.assertEquals(Condition.DECOMMISSIONED, s.getCondition());
        }
    }
}
