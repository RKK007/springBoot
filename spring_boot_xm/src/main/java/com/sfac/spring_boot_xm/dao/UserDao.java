package com.sfac.spring_boot_xm.dao;

import com.sfac.spring_boot_xm.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/*
@Home:Lenovo
@author:REN
@program:spring_boot_xm
@date:2020-09-09 10:50:53
@description:
*/
@Repository
@Mapper
public interface UserDao {
    @Insert("insert into user(username,password) values(#{username},#{password})")
    int register(User user);
}
