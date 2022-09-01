package com.practice.scooterrentalspringapplication.aspect;

import com.practice.scooterrentalspringapplication.exception.ScooterNotFoundException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Component
@Aspect
public class GenericAspect {

    private final static Logger LOG = LoggerFactory.getLogger(GenericAspect.class);

    //Normal aspects
    //Send advice before find methods
    @Before("execution(* com.practice.scooterrentalspringapplication.service.*.find*())")
    public void getAllAdviceNoParam()
    {
        LOG.info("Service method GET called");
    }

    @Before("execution(* com.practice.scooterrentalspringapplication.service.*.find*(*))")
    public void getAllAdvice()
    {
        LOG.info("Service method GET called");
    }

    //Pointcuts
    @Before("allMethodsPointcut()")
    public void allServiceMethodsAdvice()
    {
        LOG.info("Before executing service method");
    }

    @Pointcut("within(com.practice.scooterrentalspringapplication.service.*)")
    public void allMethodsPointcut(){}

    @After("allMethodsPointcut()")
    public void serviceSuccessful()
    {
        LOG.info("Service method successfully executed.");
    }

    //JoinPointcuts
    @Before("execution(public void com.practice.scooterrentalspringapplication.service.*.add*())")
    public void loggingAdviceScooter(JoinPoint joinPoint)
    {
        LOG.info("Before running loggingAdvice on method: " + joinPoint.toString());
        LOG.info("Arguments passed: " + Arrays.toString(joinPoint.getArgs()));
    }

    //After Aspects
    @AfterThrowing(pointcut = "execution(* com.practice.scooterrentalspringapplication.service.ScooterService.*(..))", throwing = "ex")
    public void logExceptions(ScooterNotFoundException ex)
    {
        LOG.info("Exception thrown in: " + ex.getMessage());
    }

    //Around aspects
    //TODO For some reason it doesn't work on findAll methods
    @Around("execution(* com.practice.scooterrentalspringapplication.repository.*.find*())")
    public Object genericAroundAdviceNoParam(ProceedingJoinPoint proceedingJoinPoint)
    {
        LOG.info("Before invoking repository find method");
        Object value = null;
        try
        {
            value = proceedingJoinPoint.proceed();
        }
        catch (Throwable ex)
        {
            ex.printStackTrace();
        }
        LOG.info("After invoking repository find method. Return value: " + value);
        return value;
    }

    @Around("execution(* com.practice.scooterrentalspringapplication.repository.*.find*(*))")
    public Object genericAroundAdvice(ProceedingJoinPoint proceedingJoinPoint)
    {
        LOG.info("Before invoking repository find method");
        Object value = null;
        try
        {
            value = proceedingJoinPoint.proceed();
        }
        catch (Throwable ex)
        {
            ex.printStackTrace();
        }
        LOG.info("After invoking repository find method. Return value: " + value);
        return value;
    }
}
