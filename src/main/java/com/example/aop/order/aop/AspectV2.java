package com.example.aop.order.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import lombok.extern.slf4j.Slf4j;

/**
 *@title : AspectV2
 *@author : wikyubok 
 *@date : "2021-11-23 14:38:53"
 *@description : Aspect V2 / pointcut을 따로 나눠서 활용하기, 하나의 포인트컷에 여러 advice를 적용할 수 있음
*/

@Slf4j
@Aspect
public class AspectV2 {

    //
    @Pointcut("execution(* com.example.aop.order..*(..))")
    private void allOrder() { // pointcut signature 
    }

    @Around("allOrder()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature());
        return joinPoint.proceed();
    }

    // @Around("allOrder()")
    // public Object doLog2(ProceedingJoinPoint joinPoint) throws Throwable {
    //     log.info("[log] {}", joinPoint.getSignature());
    //     return joinPoint.proceed();
    // }

}

