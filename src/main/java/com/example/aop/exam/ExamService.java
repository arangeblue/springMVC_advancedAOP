package com.example.aop.exam;

import com.example.aop.exam.annotation.Trace;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


/**
 *@title : ExamService
 *@author : wikyubok 
 *@date : "2021-11-25 16:14:32"
 *@description : ExamService / aop 실전 예제를 위한 service 코드
*/

@Service
@RequiredArgsConstructor
public class ExamService {
    
    private final ExamRepository examRepository;


    @Trace
    public void request(String itemId) {
        examRepository.save(itemId);
    }
}
