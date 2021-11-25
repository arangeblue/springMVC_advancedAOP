package com.example.aop.exam.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *@title : Trace
 *@author : wikyubok 
 *@date : "2021-11-25 16:24:46"
 *@description : Trace / aop 실전 에제에 활용할 annotation, 이 annotation이 붙은 method에 적용
*/

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Trace {
    
}
