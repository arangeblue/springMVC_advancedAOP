package com.example.aop.order.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

import lombok.extern.slf4j.Slf4j;

/**
 *@title : AspectV5Order
 *@author : wikyubok 
 *@date : "2021-11-23 17:20:44"
 *@description : AspectV5Order / Aspect에서 순서를 정하는 코드
*/


@Slf4j
public class AspectV5Order {


    @Order(1)
    @Aspect
    public static class LogAspect {

        @Around("com.example.aop.order.aop.Pointcuts.allOrder()")
        public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[log] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }

    
    @Order(2)
    @Aspect
    public static class transactionAspect {

        @Around("com.example.aop.order.aop.Pointcuts.orderAndService()")
        public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {

            try {
                log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
                Object result = joinPoint.proceed();
                log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
                return result;
            } catch (Exception e) {
                log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
                throw e;
            } finally {
                log.info("[리소스 릴리스] {}", joinPoint.getSignature());
            }
        }

    }

    
    
}
