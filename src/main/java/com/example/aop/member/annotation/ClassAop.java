package com.example.aop.member.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 *@title : ClassAop
 *@author : wikyubok 
 *@date : "2021-11-24 14:30:28"
 *@description : ClassAop / pointcut 지시자를 만들기 위한 class annotation
*/

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ClassAop {
    
}
