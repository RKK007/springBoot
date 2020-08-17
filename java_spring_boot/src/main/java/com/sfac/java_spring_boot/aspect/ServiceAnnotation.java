package com.sfac.java_spring_boot.aspect;
import java.lang.annotation.*;

@Target(ElementType.METHOD)//方法上的注解
@Documented//当前注解类为文档类
@Retention(RetentionPolicy.RUNTIME)//指定作用的范围为运行时
public @interface ServiceAnnotation {
    String value() default "aaa";
}
