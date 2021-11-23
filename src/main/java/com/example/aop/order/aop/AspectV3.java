package com.example.aop.order.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
public class AspectV3 {
    

    // com.examplee.aop.order 하위 패키지
    @Pointcut("execution(* com.example.aop.order..*(..))")
    private void allOrder() {
    }


    // 클래스 이름 패턴이 *Service / *..(패키지명) , *Service(class 명), *(..)(메서드 명)
    @Pointcut("execution(* *..*Service.*(..))")
    private void allService(){}


    @Around("allOrder()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature());
        return joinPoint.proceed();
    }

    // com.example.aop.order 패키지 하위에 있으면서 이름 패턴이 *Service인 것
    @Around("allOrder() && allService()")
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
