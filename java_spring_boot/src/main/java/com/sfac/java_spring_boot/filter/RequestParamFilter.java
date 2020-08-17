package com.sfac.java_spring_boot.filter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/*
@Home:Lenovo
@author:REN
@program:java_spring_boot
@date:2020-08-17 09:22:22
@description:
*/
@WebFilter(filterName = "requestParamFilter",urlPatterns = "/**")
public class RequestParamFilter implements Filter {
    private final static Logger LOGGER = LoggerFactory.getLogger(RequestParamFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.debug("===============Init Request Param Filter==================");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOGGER.debug("===============doFilter Request Param Filter==================");
        HttpServletRequest httpRequest = (HttpServletRequest)request;

        //springMvc提供的工具类，过滤springBoot请求的参数
        HttpServletRequestWrapper wapper = new HttpServletRequestWrapper(httpRequest){
            //过滤普通request的参数
            @Override
            public String getParameter(String name) {
                String value = httpRequest.getParameter(name);
                if(StringUtils.isNoneBlank(value)){
                    return value.replaceAll("fuck","****");
                }
                return super.getParameter(name);
            }

            //过滤注解接收的参数
            @Override
            public String[] getParameterValues(String name) {
                String[] values = httpRequest.getParameterValues(name);
                if(values != null && values.length>0){
                    for (int a=0;a<values.length;a++){
                        values[a] = values[a].replaceAll("fuck","****");
                    }
                    return values;
                }
                return super.getParameterValues(name);
            }
        };
        //执行完后要放行请求
        chain.doFilter(wapper,response);
    }

    @Override
    public void destroy() {
        LOGGER.debug("===============Destroy Request Param Filter==================");
    }
}
