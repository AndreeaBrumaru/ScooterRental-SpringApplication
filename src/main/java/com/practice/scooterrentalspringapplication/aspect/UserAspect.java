package com.practice.scooterrentalspringapplication.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class UserAspect {
    private final static Logger LOG = LoggerFactory.getLogger(UserAspect.class);

    //Adding user advice
    @Before("execution(public void com.practice.scooterrentalspringapplication.service.UserService.addUser(*))")
    public void loggingAdviceUser(JoinPoint joinPoint)
    {
        LOG.info("Before running loggingAdvice on method: " + joinPoint.toString());
        LOG.info("Arguments passed: " + Arrays.toString(joinPoint.getArgs()));
    }

    //Start ride advice
    @Pointcut("execution(public void startRide(*, *, *))")
    public void startRidePointcut(){}

    @Before("startRidePointcut()")
    public void loggingStartRideAdvice()
    {
        LOG.info("startRide() has been called");
    }
}
