package com.example.aop.pointcut;

import com.example.aop.member.MemberService;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import lombok.extern.slf4j.Slf4j;

/**
 *@title : ThisTargetTest
 *@author : wikyubok 
 *@date : "2021-11-25 15:00:58"
 *@description : ThisTargetTest / this와 target의 차이를 테스트 
 *cglib은 구체클래스를 바라보고 있기 때문에 this객체가 구체클래스를 인식할 수 있으며, jdk 동적 프록시에서는 프록시가 interface를 바라보고 생성되어 있기 때문에 this에서 구체클래스를 인식하고 있지 않는다.
*/

/**
 * application.properties 
 * spring.aop.proxy-target-class=true CGLIB
 * spring.aop.proxy-target-class=false JDK 동적 프록시
 */


@Slf4j
@Import(ThisTargetTest.ThisTargetAspect.class)
@SpringBootTest(properties = "spring.aop.proxy-target-class=true")
public class ThisTargetTest {
    
    @Autowired
    MemberService memberService;

    @Test
    void success() {
        log.info("memberService Proxy = {}", memberService.getClass());
        memberService.hello("helloA");
    }

    @Aspect
    static class ThisTargetAspect {
        

        
        @Around("this(com.example.aop.member.MemberService)")
        public Object doThisInterface(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[this-interface] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

    
        @Around("target(com.example.aop.member.MemberService)")
        public Object doTargetInterface(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[target-interface] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

        
        
        @Around("this(com.example.aop.member.MemberServiceImpl)")
        public Object doThisImpl(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[this-impl] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

        // 부모타입 허용
        @Around("target(com.example.aop.member.MemberServiceImpl)")
        public Object doTargetImpl(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[target-impl] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }



    }

}
