package com.example.aop.member.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @title : MethodAop
 * @author : wikyubok
 * @date : "2021-11-24 14:34:16"
 * @description : MethodAop / pointcut 지시자를 만들기 위한 method annotation
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodAop {
    String value();
}
