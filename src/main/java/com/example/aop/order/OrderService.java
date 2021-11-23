package com.example.aop.order;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;


/**
 *@title : OrderService
 *@author : wikyubok 
 *@date : "2021-11-16 15:47:53"
 *@description : aop 구현 예시를 위한 service
*/


@Slf4j
@Service
public class OrderService {
    
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void orderItem(String itemId) {
        log.info("[orderService] 실행");

        orderRepository.save(itemId);
    }
}
