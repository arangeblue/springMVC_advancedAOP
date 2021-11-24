
package com.example.aop.member;

import com.example.aop.member.annotation.ClassAop;
import com.example.aop.member.annotation.MethodAop;

import org.springframework.stereotype.Component;

/**
 *@title : MemberServiceImpl
 *@author : wikyubok 
 *@date : "2021-11-24 14:35:04"
 *@description : MemberServiceImpl / pointcut 지시자 예제를 위한 memberServiceImpl 
*/

@ClassAop
@Component
public class MemberServiceImpl implements MemberService{

    @Override
    @MethodAop("test value")
    public String hello(String param) {
        return "ok";
    }
    
    public String internal(String param) {
        return "ok";
    }

}
