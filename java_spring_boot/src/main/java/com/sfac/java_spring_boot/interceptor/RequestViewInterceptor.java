package com.sfac.java_spring_boot.interceptor;

import com.sfac.java_spring_boot.filter.RequestParamFilter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
@Home:Lenovo
@author:REN
@program:java_spring_boot
@date:2020-08-17 10:28:16
@description:
1、注册为spring主键
2、实现自HandlerInterceptor接口
3、重写preHandle、postHandle、afterCompletion三个方法
*/
@Component
public class RequestViewInterceptor implements HandlerInterceptor {
    private final static Logger LOGGER = LoggerFactory.getLogger(RequestParamFilter.class);

    //返回true才会执行下边两个方法，false就直接结束该拦截器
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.debug("===============拦截器处理之前 Request Param Filter==================");
        return HandlerInterceptor.super.preHandle(request,response,handler);
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        LOGGER.debug("===============拦截器处理中 Request Param Filter==================");

        if(modelAndView == null || modelAndView.getViewName().startsWith("redirect")){
            return;
        }

        String path = request.getServletPath();//获取请求路径，path为请求的地址，例test/index
        String template = (String)modelAndView.getModelMap().get("template");
        if(StringUtils.isBlank(template)){
            if(path.startsWith("/")){
                path = path.substring(1);
            }
            modelAndView.getModelMap().addAttribute("template",path.toLowerCase());
        }
        HandlerInterceptor.super.preHandle(request,response,handler);
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LOGGER.debug("===============拦截器处理之后 Request Param Filter==================");
        HandlerInterceptor.super.preHandle(request,response,handler);
    }
}
