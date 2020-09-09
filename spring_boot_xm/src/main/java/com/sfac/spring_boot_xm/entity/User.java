package com.sfac.spring_boot_xm.entity;

import lombok.Data;

/*
@Home:Lenovo
@author:REN
@program:spring_boot_xm
@date:2020-09-09 10:49:30
@description:
*/
@Data
public class User {
    private long userId;
    private String username;
    private String password;
}
