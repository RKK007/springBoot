package com.sfac.java_spring_boot.models.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
@Home:Lenovo
@author:REN
@program:java_spring_boot
@date:2020-08-17 13:41:50
@description:
*/
@Controller
@RequestMapping("/test")
public class AccountController {
    /**
     * 127.0.0.1/test/log
     */
    @GetMapping("/log")
    public String usersPage(){
        return "index";
    }
}
