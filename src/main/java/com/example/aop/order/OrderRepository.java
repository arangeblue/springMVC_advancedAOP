package com.example.aop.order;

import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;


/**
 *@title : OrderRepository
 *@author : wikyubok 
 *@date : "2021-11-16 15:47:30"
 *@description : aop 테스트 예시를 위한 repository
*/

@Slf4j
@Repository
public class OrderRepository {
    
    public String save(String itemId) {
        log.info("[orderRepository] 실행");


        if (itemId.equals("ex")) {
            throw new IllegalArgumentException("예외 발생!");

        }
    
        return "ok";
    }

}
