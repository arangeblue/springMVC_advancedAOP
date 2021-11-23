package com.example.aop.order.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import lombok.extern.slf4j.Slf4j;

/**
 *@title : AspectV1
 *@author : wikyubok 
 *@date : "2021-11-16 16:02:19"
 *@description : Aspect를 적용하기 위한 코드 / V1 
*/


@Slf4j
@Aspect
public class AspectV1 {
    
    @Around("execution(* com.example.aop.order..*(..))") // pointcut
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable{ // advice
        log.info("[log] {}", joinPoint.getSignature());
        return joinPoint.proceed();
    }

}
