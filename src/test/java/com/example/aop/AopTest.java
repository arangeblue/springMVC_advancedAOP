package com.example.aop;

import com.example.aop.order.OrderRepository;
import com.example.aop.order.OrderService;
import com.example.aop.order.aop.AspectV4Pointcuts;
import com.example.aop.order.aop.AspectV5Order;
import com.example.aop.order.aop.AspectV6Advice;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import lombok.extern.slf4j.Slf4j;

/**
 *@title : AopTest
 *@author : wikyubok 
 *@date : "2021-11-16 15:48:09"
 *@description : aop테스트 코드
*/



@Slf4j
// @Import(AspectV1.class)
// @Import(AspectV2.class)
// @Import(AspectV3.class)
// @Import(AspectV4Pointcuts.class)
// @Import({ AspectV5Order.LogAspect.class, AspectV5Order.transactionAspect.class })
@Import(AspectV6Advice.class)
@SpringBootTest
public class AopTest {
    
    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;


    @Test
    @DisplayName("aop 프록시 적용 테스트")
    public void aopInfo() {

        log.info("isAopProxy, orderService = {}", AopUtils.isAopProxy(orderService));
        log.info("isAopProxy, orderRepository = {}", AopUtils.isAopProxy(orderRepository));

    }

    @Test
    @DisplayName("orderService test")
    public void success() {

        orderService.orderItem("itemA");

    }
    
    @Test
    @DisplayName("orderService exception test")
    public void exception() {

        Assertions.assertThatThrownBy(() -> orderService.orderItem("ex")).isInstanceOf(IllegalStateException.class);
    }
    
}

