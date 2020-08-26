package com.sfac.java_spring_boot.models.account.service;

import com.github.pagehelper.PageInfo;
import com.sfac.java_spring_boot.models.account.entity.User;
import com.sfac.java_spring_boot.models.common.vo.Result;
import com.sfac.java_spring_boot.models.common.vo.SearchVo;
import org.springframework.web.multipart.MultipartFile;

/*
@Home:Lenovo
@author:REN
@program:java_spring_boot
@date:2020-08-20 09:01:53
@description:
*/
public interface UserService {
    Result<User> insertUser(User user);

    Result<User> login(User user);

    PageInfo<User> getUsersBySearchVo(SearchVo searchVo);

    Result<User> updateUser(User user);

    Result<Object> deleteUser(int userId);

    User getUserByUserId(int userId);

    Result<String> uploadUserImg(MultipartFile file);

    Result<User> updateUserProfile(User user);

    User getUserByUserName(String userName);

    void logout();
}
