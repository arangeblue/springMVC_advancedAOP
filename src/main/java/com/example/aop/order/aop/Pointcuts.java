package com.example.aop.order.aop;

import org.aspectj.lang.annotation.Pointcut;

/**
 *@title : Pointcuts
 *@author : wikyubok 
 *@date : "2021-11-23 16:47:01"
 *@description : Pointcuts, pointcut을 외부파일로 만들어서 호출하기 위한 코드
*/
 

public class Pointcuts {
 
    // com.examplee.aop.order 하위 패키지
    @Pointcut("execution(* com.example.aop.order..*(..))")
    public void allOrder() {
    }

    // 클래스 이름 패턴이 *Service / *..(패키지명) , *Service(class 명), *(..)(메서드 명)
    @Pointcut("execution(* *..*Service.*(..))")
    public void allService() {
    }

    // order와 service 조합
    @Pointcut("allOrder() && allService()")
    public void orderAndService() {
    }

}
