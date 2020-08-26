package com.sfac.java_spring_boot.models.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sfac.java_spring_boot.config.ResourceConfigBean;
import com.sfac.java_spring_boot.models.account.dao.UserDao;
import com.sfac.java_spring_boot.models.account.dao.UserRoleDao;
import com.sfac.java_spring_boot.models.account.entity.Role;
import com.sfac.java_spring_boot.models.account.entity.User;
import com.sfac.java_spring_boot.models.account.service.UserService;
import com.sfac.java_spring_boot.models.common.vo.Result;
import com.sfac.java_spring_boot.models.common.vo.SearchVo;

import com.sfac.java_spring_boot.utils.MD5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Collections;

import java.util.List;
import java.util.Optional;





import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/*
@Home:Lenovo
@author:REN
@program:java_spring_boot
@date:2020-08-20 09:02:33
@description:
*/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    /**
     * 插入数据之前确认数据库不存在该用户名
     */
    //用户注册
    @Override
    public Result<User> insertUser(User user) {
        SimpleDateFormat sm = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
        user.setCreateDate(LocalDateTime.now());
        user.setPassword(MD5Util.getMD5(user.getPassword()));
        if(userDao.getUserName(user.getUserName())!=null){
            return new Result<User>(Result.ResultStatus.FAILD.status,"userName is exists",user);
        }else {
            userDao.insertUser(user);
            return new Result<User>(Result.ResultStatus.SUCCESS.status,"Insert success",user);
        }
    }


    //用户登陆
    @Override
    public Result<User> login(User user) {
//        user.setPassword(MD5Util.getMD5(user.getPassword()));
//        User u = userDao.login(user);
//        if(u!=null){
//            return new Result<User>(Result.ResultStatus.SUCCESS.status,"login success",user);
//        }else {
//            return new Result<User>(Result.ResultStatus.FAILD.status,"login Error",user);
//        }
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUserName(),MD5Util.getMD5(user.getPassword()));
        usernamePasswordToken.setRememberMe(user.getRememberMe());

        try {
            subject.login(usernamePasswordToken);//进入认证方法
            subject.checkRoles();
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<User>(Result.ResultStatus.FAILD.status, "UserName or password is error.");
        }

        Session session = subject.getSession();
        session.setAttribute("user", (User)subject.getPrincipal());

        return new Result<User>(Result.ResultStatus.SUCCESS.status, "Login success.", user);
    }


    //分页查询
    @Override
    public PageInfo<User> getUsersBySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        return new PageInfo<User>(
                Optional.ofNullable(userDao.getUsersBySearchVo(searchVo))
                        .orElse(Collections.emptyList()));
    }


/*======================================================================================================================================*/


    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private ResourceConfigBean resourceConfigBean;

    @Override
    @Transactional
    public Result<User> updateUser(User user) {
        User userTemp = userDao.getUserName(user.getUserName());
        if (userTemp != null && userTemp.getUserId() != user.getUserId()) {
            System.err.println(user.getUserName()+"========"+userTemp.getUserName()+"======"+user.getUserId()+"========="+userTemp.getUserId());
            return new Result<User>(Result.ResultStatus.FAILD.status, "User name is repeat.");
        }

        userDao.updateUser(user);

        userRoleDao.deleteUserRoleByUserId(user.getUserId());
        List<Role> roles = user.getRoles();
        if (roles != null && !roles.isEmpty()) {
            roles.stream().forEach(item -> {
                userRoleDao.addUserRole(user.getUserId(), item.getRoleId());
            });
        }

        return new Result<User>(
                Result.ResultStatus.SUCCESS.status, "Update success.", user);
    }

    @Override
    @Transactional
    public Result<Object> deleteUser(int userId) {
        userDao.deleteUser(userId);
        userRoleDao.deleteUserRoleByUserId(userId);
        return new Result<>(Result.ResultStatus.SUCCESS.status, "Delete success.");
    }

    @Override
    public User getUserByUserId(int userId) {
        return userDao.getUserByUserId(userId);
    }

    @Override
    public Result<String> uploadUserImg(MultipartFile file) {
        if (file.isEmpty()) {
            return new Result<String>(Result.ResultStatus.FAILD.status, "Please select img.");
        }

        String relativePath = "";
        String destFilePath = "";
        try {
            String osName = System.getProperty("os.name");
            if (osName.toLowerCase().startsWith("win")) {
                destFilePath = resourceConfigBean.getLocationPathForWindows() +
                        file.getOriginalFilename();
            } else {
                destFilePath = resourceConfigBean.getLocationPathForLinux()
                        + file.getOriginalFilename();
            }
            relativePath = resourceConfigBean.getRelativePath() +
                    file.getOriginalFilename();
            File destFile = new File(destFilePath);
            file.transferTo(destFile);

        } catch (IOException e) {
            e.printStackTrace();
            return new Result<String>(
                    Result.ResultStatus.FAILD.status, "Upload failed.");
        }

        return new Result<String>(
                Result.ResultStatus.SUCCESS.status, "Upload success.", relativePath);
    }

    @Override
    @Transactional
    public Result<User> updateUserProfile(User user) {
        User userTemp = userDao.getUserName(user.getUserName());
        if (userTemp != null && userTemp.getUserId() != user.getUserId()) {
            return new Result<User>(Result.ResultStatus.FAILD.status, "User name is repeat.");
        }

        userDao.updateUser(user);

        return new Result<User>(Result.ResultStatus.SUCCESS.status, "Edit success.", user);
    }

    @Override
    public User getUserByUserName(String userName) {
        return userDao.getUserName(userName);
    }

    //退出登陆销毁shiro认证信息
    @Override
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        Session session = subject.getSession();
        session.setAttribute("user", null);
    }
}
