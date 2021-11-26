package com.example.aop.internalcall;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 *@title : InternalService
 *@author : wikyubok 
 *@date : "2021-11-26 16:43:42"
 *@description : InternalService / 내부호출의 문제를 외부 클래스로 만들어서 해결
*/


@Slf4j
@Component
public class InternalService {
    
    public void internal() {
        log.info("call internal");
    }
}
