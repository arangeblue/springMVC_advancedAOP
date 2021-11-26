package com.example.aop.internalcall;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *@title : CallServiceV3
 *@author : wikyubok 
 *@date : "2021-11-26 16:32:57"
 *@description : CallServiceV3 / 프록시 내부 호출에 대한 대안 3. 구조를 변경하여 해결하는 방법( 가장 권장 )
*/

@Slf4j
@Component
@RequiredArgsConstructor
public class CallServiceV3 {
    
    private final InternalService internalService;

    public void external() {
        log.info("call external");
        internalService.internal(); // 외부 메서드 호출
    }

}
