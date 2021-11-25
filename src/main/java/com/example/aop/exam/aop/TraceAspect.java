package com.example.aop.exam.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import lombok.extern.slf4j.Slf4j;

/**
 *@title : TraceAspect
 *@author : wikyubok 
 *@date : "2021-11-25 16:24:28"
 *@description : TraceAspect / aop 실전 예제에 적용할 aspect 코드
*/

@Slf4j
@Aspect
public class TraceAspect {
    

    @Before("@annotation(com.example.aop.exam.annotation.Trace)")
    public void doTrace(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        log.info("[trace] {} args = {}", joinPoint.getSignature(), args);
        
    }
}
