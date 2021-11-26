package com.example.aop.internalcall;

import com.example.aop.internalcall.aop.CallLogAspect;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import lombok.extern.slf4j.Slf4j;

/**
 *@title : CallServiceV0Test
 *@author : wikyubok 
 *@date : "2021-11-25 17:13:36"
 *@description : CallServiceV0Test / 프록시 기술의 주의 사항 테스트 1. 내부 메서드 호출 시
*/


@Slf4j
@Import(CallLogAspect.class)
@SpringBootTest
public class CallServiceV0Test {

    @Autowired
    CallServiceV0 callServiceV0;

    @Test
    void external() {
        callServiceV0.external();
    }

    @Test
    void internal() {
        callServiceV0.internal();
        
    }
    

}
    