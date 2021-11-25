package com.example.aop.exam;

import com.example.aop.exam.annotation.Retry;
import com.example.aop.exam.annotation.Trace;

import org.springframework.stereotype.Repository;

/**
 *@title : ExamRepository
 *@author : wikyubok 
 *@date : "2021-11-25 16:11:22"
 *@description : ExamRepository / aop 실전 예제를 위한 repository 코드
*/

@Repository
public class ExamRepository {
    
    private static int seq = 0;

    /**
     * 5번에 1번 실패를 요청
     */
    @Trace
    @Retry(value = 4)
    public String save(String itemId) {
        seq++;
        if (seq % 5 == 0) {
            throw new IllegalStateException("예외 발생!!");
        }
        
        return "ok";
    }
}
