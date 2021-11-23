package com.example.aop.order.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import lombok.extern.slf4j.Slf4j;


/**
 *@title : AspectV6Advice
 *@author : wikyubok 
 *@date : "2021-11-23 17:47:00"
 *@description : AspectV6Advice / Around는 여러 조각이 합쳐져서 만들어진 annotation이다, @Before, @AfterReturning, @AfterThrowing, @After
*/

@Slf4j
@Aspect
public class AspectV6Advice {

    // @Around("com.example.aop.order.aop.Pointcuts.orderAndService()")
    // public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {

    //     try {
    //         //@Before
    //         log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
    //         Object result = joinPoint.proceed();

    //         // @AfterReturning
    //         log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
    //         return result;
    //     } catch (Exception e) {
    //         // @AfterThrowing
    //         log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
    //         throw e;
    //     } finally {
    //         // @After
    //         log.info("[리소스 릴리스] {}", joinPoint.getSignature());
    //     }
    // }

    // joinpoint의 실행전에 실행해주는 것만 적용해주면 자동으로 joinpoint.proceed()를 해준다.
    @Before("com.example.aop.order.aop.Pointcuts.orderAndService()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("[before] {}", joinPoint.getSignature());
    }

    @AfterReturning(value = "com.example.aop.order.aop.Pointcuts.orderAndService()", returning = "result") // return되는 값의 이름을 적어둬야 함
    public void doReturn(JoinPoint joinPoint, Object result) {
        log.info("[return] {}, return = {}", joinPoint.getSignature(), result);
    }
    
    @AfterThrowing(value = "com.example.aop.order.aop.Pointcuts.orderAndService()", throwing = "ex")
    public void doThrowing(JoinPoint joinPoint, Exception ex) {
        log.info("[ex] {}, message = {}", ex);
    }

    @After(value = "com.example.aop.order.aop.Pointcuts.orderAndService()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("[after] {}", joinPoint.getSignature());
    }
    
}