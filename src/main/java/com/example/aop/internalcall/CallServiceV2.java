package com.example.aop.internalcall;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 *@title : CallServiceV2
 *@author : wikyubok 
 *@date : "2021-11-26 16:24:35"
 *@description : CallServiceV2 / 프록시 내부 호출 문제 대안 2, applicationContext, ObjectProvider (지연호출) 를 활용한 방법 
*/

@Slf4j
@Component
public class CallServiceV2 {
    

    // private final ApplicationContext applicationContext;
    private final ObjectProvider<CallServiceV2> callServiceProvider;


    public CallServiceV2(ObjectProvider<CallServiceV2> callServiceProvider) {
        this.callServiceProvider = callServiceProvider;
    }

    // 지연 호출
    public void external() {
        log.info("call external");
        CallServiceV2 callServiceV2 = callServiceProvider.getObject();
        // CallServiceV2 callServiceV2 = applicationContext.getBean(CallServiceV2.class);
        callServiceV2.internal();
    }

    public void internal() {
        log.info("call internal");
    }

}
