package com.sfac.java_spring_boot.config;

import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
@author:Lenovo
@program:java_spring_boot
@date:2020-08-11 10:28:19
@description:
*/
@Configuration
@AutoConfigureAfter({WebMvcAutoConfiguration.class})
public class WebMvcConfig {
    @Value("${server.http.port}")
    private int port;


    //配置连接器
    @Bean
    public Connector connector(){
        Connector connector = new Connector();
        connector.setPort(port);
        connector.setScheme("http");
        return connector;
    }



    @Bean
    public ServletWebServerFactory sbf(){
        TomcatServletWebServerFactory tswf = new TomcatServletWebServerFactory();
        tswf.addAdditionalTomcatConnectors(connector());
        return tswf;
    }
}

