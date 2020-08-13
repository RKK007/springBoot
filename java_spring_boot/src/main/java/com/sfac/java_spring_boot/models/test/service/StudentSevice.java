package com.sfac.java_spring_boot.models.test.service;


import com.sfac.java_spring_boot.models.common.vo.Result;
import com.sfac.java_spring_boot.models.common.vo.SearchVo;
import com.sfac.java_spring_boot.models.test.entity.Student;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Description StudentSevice
 * @Author HymanHu
 * @Date 2020/8/12 15:32
 */
public interface StudentSevice {

    Result<Student> insertStudent(Student student);

    Student findStudentById(int studentId);

    Page<Student> getStudentBySearchVo(SearchVo searchVo);

    List<Student> getStudentsByStudentName(String studentName);

    List<Student> getStudentsByStudentNameLike(String studentName);

    List<Student> getTop2StudentsByStudentNameLike(String studentName);

    List<Student> getStudentsByParams(String studentName,int cardId);

}

















