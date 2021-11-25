package com.example.aop.exam;

import com.example.aop.exam.aop.RetryAspect;
import com.example.aop.exam.aop.TraceAspect;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import lombok.extern.slf4j.Slf4j;

/**
 *@title : ExamTest
 *@author : wikyubok 
 *@date : "2021-11-25 16:31:03"
 *@description : ExamTest / aop 실전 예제 테스트를 위한 코드
*/


@Slf4j
@Import({ TraceAspect.class, RetryAspect.class})
@SpringBootTest
public class ExamTest {
    

    @Autowired
    ExamService examService;

    @Test
    void test() {
        for (int i = 0; i < 5; i++) {
            log.info("client request i = {}", i);
            examService.request("data" + i);
        }
    }
}
