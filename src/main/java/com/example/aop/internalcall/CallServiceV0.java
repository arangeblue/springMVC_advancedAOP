package com.example.aop.internalcall;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;


/**
 *@title : CallServiceV0
 *@author : wikyubok 
 *@date : "2021-11-25 17:09:09"
 *@description : CallServiceV0 / 프록시 기술을 사용할 때 주의해야 할 점 - 내부 메서드 호출 시
*/

@Slf4j
@Component
public class CallServiceV0 {
    
    public void external() {
        log.info("call external");
        internal(); // 내부 메서드 호출 (this.internal())
    }

    public void internal() {
        log.info("call internal");
    }

}
