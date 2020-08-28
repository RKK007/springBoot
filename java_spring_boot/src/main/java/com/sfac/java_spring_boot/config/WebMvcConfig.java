package com.sfac.java_spring_boot.config;

import com.sfac.java_spring_boot.filter.RequestParamFilter;
import com.sfac.java_spring_boot.interceptor.RequestViewInterceptor;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
@author:Lenovo
@program:java_spring_boot
@date:2020-08-11 10:28:19
@description:
*/
@Configuration//该类为接口类
@AutoConfigureAfter({WebMvcAutoConfiguration.class})
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${server.http.port}")
    private int port;


    //配置连接器协议和端口
    @Bean
    public Connector connector(){
        Connector connector = new Connector();
        connector.setPort(port);
        connector.setScheme("http");
        return connector;
    }
    //将配置加入Tomcat容器
    @Bean
    public ServletWebServerFactory sbf(){
        TomcatServletWebServerFactory tswf = new TomcatServletWebServerFactory();
        tswf.addAdditionalTomcatConnectors(connector());
        return tswf;
    }





    //注册request过滤器
    @Bean
    public FilterRegistrationBean<RequestParamFilter> register(){
        FilterRegistrationBean<RequestParamFilter> register = new FilterRegistrationBean<>();
        register.setFilter(new RequestParamFilter());
        return register;
    }



    //注册拦截器
    @Autowired
    private RequestViewInterceptor requestViewInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestViewInterceptor).addPathPatterns("/**");//将所有请求拦截
    }







    @Value("${spring.resource.path}")
    private String relativePath;

    @Value("${spring.resource.path.pattern}")
    private String relativePathPattern;

    @Value("${spring.resource.folder.windows}")
    private String localPathForWindows;

    @Value("${spring.resource.folder.linux}")
    private String localPathForLinux;

    //注册本地资源文件
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String systemName = System.getProperty("os.name");
        //判断系统的类型
        if(systemName.toLowerCase().startsWith("win")){
            registry.addResourceHandler(relativePathPattern)
                    .addResourceLocations(ResourceUtils.FILE_URL_PREFIX+localPathForWindows);
        }else{
            registry.addResourceHandler(relativePathPattern)
                    .addResourceLocations(ResourceUtils.FILE_URL_PREFIX+localPathForLinux);
        }
    }
}

