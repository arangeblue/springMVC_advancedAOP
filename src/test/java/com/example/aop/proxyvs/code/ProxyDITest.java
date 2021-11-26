package com.example.aop.proxyvs.code;

import com.example.aop.member.MemberService;
import com.example.aop.member.MemberServiceImpl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import lombok.extern.slf4j.Slf4j;

/**
 *@title : ProxyDITest
 *@author : wikyubok 
 *@date : "2021-11-26 17:33:45"
 *@description : ProxyDITest / 프록시 기술의 한계 3. 의존성주입 시 발생하는 문제
*/

@Slf4j
@Import(ProxyDIAspect.class)
// @SpringBootTest(properties = "spring.aop.proxy-target-class=false") // JDK 동적 프록시 (False), cglib (True)
@SpringBootTest(properties = "spring.aop.proxy-target-class=true") // JDK 동적 프록시 (False), cglib (True)
public class ProxyDITest {
    
    @Autowired
    MemberService memberService;

    @Autowired
    MemberServiceImpl memberServiceImpl;

    @Test
    void go() {
        log.info("memberService class = {}", memberService.getClass());
        log.info("memberServiceImpl class = {}", memberServiceImpl.getClass());

        memberServiceImpl.hello("hello");
    }


}
