package com.example.aop.proxyvs;

import com.example.aop.member.MemberService;
import com.example.aop.member.MemberServiceImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

import lombok.extern.slf4j.Slf4j;


/**
 *@title : ProxyCastingTest
 *@author : wikyubok 
 *@date : "2021-11-26 16:43:29"
 *@description : ProxyCastingTest / 프록시 기술의 한계 2. 타입 캐스팅, jdk 동적 프록시와 cglib 프록시와의 의존차이때문에 구체 클래스를 캐스팅할 때 문제가 발생
*/

@Slf4j
public class ProxyCastingTest {

    @Test
    void jdkProxy() {

        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(false); // JDK 동적 프록시

        //프록시를 인터페이스로 케스팅, 성공
        MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();

        // JDK 동적 프록시를 구체 클래스로 캐스팅, 실패 ClassCastException 발생
        Assertions.assertThrows(ClassCastException.class, () -> {
            MemberServiceImpl casting = (MemberServiceImpl) memberServiceProxy;
        }); // 구체 클래스로는 케스팅할 수 없다.

    }

    @Test
    void cglibProxy() {

        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(true); // cglib 프록시

        // 프록시를 인터페이스로 케스팅, 성공
        MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();

        log.info("proxy class = {}", memberServiceProxy.getClass());

        // cglib 프록시를 구체 클래스로 캐스팅, 성공 
        MemberServiceImpl casting = (MemberServiceImpl) memberServiceProxy;
    }


    
}


