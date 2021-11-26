package com.example.aop.internalcall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 *@title : CallServiceV1
 *@author : wikyubok 
 *@date : "2021-11-26 16:03:36"
 *@description : CallServiceV1 / 프록시 내부 호출에 대한 대안 1, 자기 자신을 불러서 
*/


@Slf4j
@Component
public class CallServiceV1 {
    
    private CallServiceV1 callServiceV1;


    @Autowired
    public void setCallServiceV1(CallServiceV1 callServiceV1) {
        log.info("callServiceV1 setter = {}", callServiceV1.getClass());
        this.callServiceV1 = callServiceV1;
    }

    public void external() {
        log.info("call external");
        callServiceV1.internal();
    }

    public void internal() {
        log.info("call internal");
        
    }

}
