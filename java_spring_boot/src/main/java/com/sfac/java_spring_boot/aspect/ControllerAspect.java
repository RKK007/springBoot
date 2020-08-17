package com.sfac.java_spring_boot.aspect;

import com.sfac.java_spring_boot.filter.RequestParamFilter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/*
@Home:Lenovo
@author:REN
@program:java_spring_boot
@date:2020-08-17 13:52:24
@description:
AOP顺序：around->before->after
*/
@Aspect//代表切面程序
@Component//注册为主键
public class ControllerAspect {
    private final static Logger LOGGER = LoggerFactory.getLogger(RequestParamFilter.class);

    /*
    *pointCut()对方法进行切面
    * 第一个*返回类型不限
    * 第二个*代表models下所有子包
    * 第三个代表所有类
    * 第四个代表类的所有方法
    * */
    @Pointcut("execution(public * com.sfac.java_spring_boot.models.*.controller.*.testDesc(..))")
    @Order(1)
    public void controllerPointCut(){
    }

    /*前置通知*/
    @Before(value = "com.sfac.java_spring_boot.aspect.ControllerAspect.controllerPointCut()")
    public void beforeController(JoinPoint joinPoint){
        LOGGER.debug("==============THIS IS BEFORE CONTROLLER=================");
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        LOGGER.debug("请求来源"+request.getRemoteAddr());
        LOGGER.debug("请求url"+request.getRequestURL().toString());
        LOGGER.debug("请求方法"+request.getMethod());
        LOGGER.debug("响应方法"+joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        LOGGER.debug("请求参数"+Arrays.toString(joinPoint.getArgs()));
    }


    /*环绕通知*/
    @Around(value = "com.sfac.java_spring_boot.aspect.ControllerAspect.controllerPointCut()")
    public  Object aroundController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        LOGGER.debug("================THIS IS AROUND CONTROLLER==================");
        return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
    }


    /*后置通知*/
    @After(value = "com.sfac.java_spring_boot.aspect.ControllerAspect.controllerPointCut()")
    public  void afterController(){
        LOGGER.debug("================THIS IS AFTER CONTROLLER==================");
    }
}
