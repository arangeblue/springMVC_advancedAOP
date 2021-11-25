package com.example.aop.pointcut;

import com.example.aop.member.MemberService;
import com.example.aop.member.annotation.ClassAop;
import com.example.aop.member.annotation.MethodAop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import lombok.extern.slf4j.Slf4j;

/**
 *@title : ParameterTest
 *@author : wikyubok 
 *@date : "2021-11-25 14:48:08"
 *@description : ParameterTest / parameter 인수를 aspect를 사용해서 넘겨받을 수 있다.
*/


@Slf4j
@Import(ParameterTest.ParameterAspect.class)
@SpringBootTest
public class ParameterTest {
    
    @Autowired
    MemberService memberService;

    @Test
    void success() {
        log.info("memberService Proxy = {}", memberService.getClass());
        memberService.hello("helloA");
    }


    @Aspect
    static class ParameterAspect {
        
        @Pointcut("execution(* com.example.aop.member..*.*(..))")
        private void allMember() {
        }
        
        @Around("allMember()")
        public Object logArgs1(ProceedingJoinPoint joinPoint) throws Throwable {
            Object args = joinPoint.getArgs()[0];
            log.info("[logArgs1 {}, arg = {}", joinPoint.getSignature(), args);
            return joinPoint.proceed();
        }
    

        @Around("allMember() && args(arg,..)")
        public Object logArgs2(ProceedingJoinPoint joinPoint, Object arg) throws Throwable {
            log.info("[logArgs2 {}, arg = {}", joinPoint.getSignature(), arg);
            return joinPoint.proceed();
        }


        @Before("allMember() && args(arg,..)")
        public void logArgs3(String arg) {
            log.info("[logArgs3],  arg = {}", arg);
        }

        @Before("allMember() && this(object)")
        public void thisArgs(JoinPoint joinPoint, MemberService object) { // 대상 프록시 객체
            log.info("[thisArgs] {},  arg = {}", joinPoint.getSignature(), object);
        }

        @Before("allMember() && target(object)")
        public void targetArgs(JoinPoint joinPoint, MemberService object) { // 실제 대상 구현체
            log.info("[targetArgs] {},  arg = {}", joinPoint.getSignature(), object);
        }

        @Before("allMember() && @target(annotation)")
        public void atTarget(JoinPoint joinPoint, ClassAop annotation) {
            log.info("[@target] {},  arg = {}", joinPoint.getSignature(), annotation);
        }

        @Before("allMember() && @within(annotation)")
        public void atWithin(JoinPoint joinPoint, ClassAop annotation) {
            log.info("[@Within] {},  arg = {}", joinPoint.getSignature(), annotation);
        }

        @Before("allMember() && @annotation(annotation)")
        public void atAnnotation(JoinPoint joinPoint, MethodAop annotation ) {
            log.info("[@annotation] {},  annotationValue = {}", joinPoint.getSignature(), annotation.value());
        }

    }

}
