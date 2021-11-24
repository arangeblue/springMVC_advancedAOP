package com.example.aop.pointcut;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Method;

import com.example.aop.member.MemberServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import lombok.extern.slf4j.Slf4j;

/**
 *@title : ExecutionTest
 *@author : wikyubok 
 *@date : "2021-11-24 14:47:02"
 *@description : ExecutionTest / pointcut 지시자 테스트 - 접근제어자(생략가능), 반환타입, 선언타입(생략가능), 메서드이름, 파라미터, 예외(생략가능)
    중요도 *****
 */

@Slf4j
public class ExecutionTest {

    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method helloMethod;

    @BeforeEach
    public void init() throws NoSuchMethodException, SecurityException {
        helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
    }

    @Test
    void printMethod() {
        // public java.lang.String
        // com.example.aop.member.MemberServiceImpl.hello(java.lang.String)
        log.info("helloMethod = {} ", helloMethod);
    }

    @Test
    void exactMatch() {
        pointcut.setExpression("execution(public String com.example.aop.member.MemberServiceImpl.hello(String))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();

    }

    @Test
    void allMatch() {
        // 접근제어자(생략), 반환 *,선언타입(생략), 메서드 *, 파라미터((..)), 예외(생략) 
        pointcut.setExpression("execution(* *(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void nameMatch() {
        // 접근제어자(생략), 반환 *,선언타입(생략), 메서드 hello, 파라미터((..)), 예외(생략)
        pointcut.setExpression("execution(* hello(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void namePatternMatch() {
        // 접근제어자(생략), 반환 *,선언타입(생략), 메서드 hel*, 파라미터((..)), 예외(생략)
        pointcut.setExpression("execution(* hel*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void namePatternMatch2() {
        // 접근제어자(생략), 반환 *,선언타입(생략), 메서드 *el*, 파라미터((..)), 예외(생략)
        pointcut.setExpression("execution(* *el*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void nameMatchFalse() {
        // 접근제어자(생략), 반환 *,선언타입(생략), 메서드 nono, 파라미터((..)), 예외(생략)
        pointcut.setExpression("execution(* nono(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
    }

    //////////////////////////////////////////////////////////////////////////////////////////

    @Test
    void packageMatch1() {
        pointcut.setExpression("execution(* com.example.aop.member.MemberServiceImpl.hello(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void packageMatch2() {
        pointcut.setExpression("execution(* com.example.aop.member.*.*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void packageMatchFalse() {
        // MemberServiceImpl이 있는 package를 특정해야한다.
        pointcut.setExpression("execution(* com.example.aop.*.*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
    }

    @Test
    void packageMatchSubPackage1() {
        // MemberServiceImpl이 있는 package를 특정해야한다.
        pointcut.setExpression("execution(* com.example.aop.member..*.*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void packageMatchSubPackage() {
        // MemberServiceImpl이 있는 package를 특정해야한다.
        pointcut.setExpression("execution(* com.example.aop..*.*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    /////////////////////////////////////////////////////////////////////////////////////////

    @Test
    void typeExactMatch() {
    
        pointcut.setExpression("execution(* com.example.aop.member.MemberServiceImpl.*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void typeMatchSuperType() {
        // 부모타입으로 매칭해도 성공, 부모타입 '=' 자식타입 
        pointcut.setExpression("execution(* com.example.aop.member.MemberService.*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void typeMatchNoSuperTypeMethodInternal() throws NoSuchMethodException, SecurityException {

        // 부모타입에 선언한 메서드까지만 매칭이 되고, 자식타입에 부모타입에 없는 메서드는 매칭되지 않는다.
        pointcut.setExpression("execution(* com.example.aop.member.MemberService.*(..))");
        Method internalMethod = MemberServiceImpl.class.getMethod("internal", String.class);
        assertThat(pointcut.matches(internalMethod, MemberServiceImpl.class)).isFalse();
    }

    @Test
    void typeMatchInternal() throws NoSuchMethodException, SecurityException {
        
        pointcut.setExpression("execution(* com.example.aop.member.MemberServiceImpl.*(..))");
        Method internalMethod = MemberServiceImpl.class.getMethod("internal", String.class);
        assertThat(pointcut.matches(internalMethod, MemberServiceImpl.class)).isTrue();
    }


    //////////////////////////////////////////////////////////////////////////////////////////


    @Test
    void argsMatch() {
        // 파라미터가 String
        pointcut.setExpression("execution(* *(String))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void argsMatchNoArg() {
        // 파라미터가 None
        pointcut.setExpression("execution(* *())");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
    }

    @Test
    void argsMatchStar() {
        // 정확하게 하나의 파라미터 허용, 모든 타입 허용
        // (Xxx)
        pointcut.setExpression("execution(* *(*))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void argsMatchAll() {
        // 여러 파라미터 허용, 모든 타입 허용
        // (), (Xxx), (Xxx, Yyy)
        pointcut.setExpression("execution(* *(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void argsMatchComplex() {
        // 여러 파라미터 허용, 모든 타입 허용
        // (String), (String, Xxx), (String, Xxx, Yyy)
        pointcut.setExpression("execution(* *(String, ..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }



}

