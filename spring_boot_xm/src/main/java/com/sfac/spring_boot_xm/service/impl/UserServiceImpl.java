package com.sfac.spring_boot_xm.service.impl;

import com.sfac.spring_boot_xm.dao.UserDao;
import com.sfac.spring_boot_xm.entity.User;
import com.sfac.spring_boot_xm.mapper.UserMapper;
import com.sfac.spring_boot_xm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
@Home:Lenovo
@author:REN
@program:spring_boot_xm
@date:2020-09-09 10:50:29
@description:
*/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserMapper userMapper;

    @Override
    public int register(User user) {
        int i = userMapper.insertSelective(user);
//        return userDao.register(user);
        return i;
    }

}
