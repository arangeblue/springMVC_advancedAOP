package com.example.aop.proxyvs.code;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import lombok.extern.slf4j.Slf4j;

/**
 *@title : ProxyDIAspect
 *@author : wikyubok 
 *@date : "2021-11-26 17:33:17"
 *@description : ProxyDIAspect / 프록시 기술의 한계 3. 의존성 주입 시 발생하는 문제
*/

@Slf4j
@Aspect
public class ProxyDIAspect {
    
    @Before("execution(* com.example.aop..*.*(..))")
    public void doTrace(JoinPoint joinPoint) {
        log.info("[proxy-DI-Advice] {}", joinPoint.getSignature());
    }


}
