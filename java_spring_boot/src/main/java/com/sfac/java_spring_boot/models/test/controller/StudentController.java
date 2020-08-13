package com.sfac.java_spring_boot.models.test.controller;


import com.sfac.java_spring_boot.models.common.vo.Result;
import com.sfac.java_spring_boot.models.common.vo.SearchVo;
import com.sfac.java_spring_boot.models.test.entity.Student;
import com.sfac.java_spring_boot.models.test.service.StudentSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import java.util.List;

/**
 * @Description StudentController
 * @Author HymanHu
 * @Date 2020/8/12 15:36
 */
@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentSevice studentSevice;

/*=====================repository父类接口操作数据库================================================================================================*/
    /**
     * 127.0.0.1/api/student
     * {"studentName":"hujiang1","studentCard":{"cardId":"1"}}
     * 插入数据到学生表
     */
    @PostMapping(value = "student", consumes = "application/json")
    public Result<Student> insertStudent(@RequestBody Student student) {
        return studentSevice.insertStudent(student);
    }

    /**
     * 127.0.0.1/api/findStudentById/1
     * 根据学生ID查询学生学习
     */
    @PostMapping(value = "findStudentById/{studentId}")
    public Student findStudentById(@PathVariable int studentId) {
        return studentSevice.findStudentById(studentId);
    }

    /**
     * 127.0.0.1/api/findStudentBySearchVo ---- post
     * {"currentPage":"1","pageSize":"5","keyWord":"hu","orderBy":"studentId","sort":"asc"}
     * 根据分页信息查询学生信息
     */
    @PostMapping(value = "findStudentBySearchVo",consumes = "application/json")
    public Page<Student> findStudentBySearchVo(@RequestBody SearchVo searchVo) {
        return studentSevice.getStudentBySearchVo(searchVo);
    }







/*=====================repository层自定义方法操作================================================================================================*/
    /**
     * 127.0.0.1/api/getStudentsByStudentName?studentName=hujiang1
     * 根据学生姓名查询学生信息
     */
    @GetMapping("getStudentsByStudentName")
    public List<Student> getStudentsByStudentName(@RequestParam String studentName){
        return studentSevice.getStudentsByStudentName(studentName);
    }

    /**
     * 127.0.0.1/api/getStudentsByStudentNameLike?studentName=hu
     * 根据学生姓名模糊查询学生信息
     */
    @GetMapping("getStudentsByStudentNameLike")
    public List<Student> getStudentsByStudentNameLike(@RequestParam String studentName){
        return studentSevice.getStudentsByStudentNameLike(studentName);
    }

    /**
     * 127.0.0.1/api/getTop2StudentsByStudentNameLike?studentName=hu
     * 根据学生姓名模糊查询学生信息，显示前2条数据
     */
    @GetMapping("getTop2StudentsByStudentNameLike")
    public List<Student> getTop2StudentsByStudentNameLike(@RequestParam String studentName){
        return studentSevice.getTop2StudentsByStudentNameLike(studentName);
    }






/*=====================repository层@Query注解查询操作================================================================================================*/
    /**
     * 127.0.0.1/api/getStudentsByParams?studentName=hujiang&cardId=1
     * 根据学生姓名和CardId查询信息
     */
    @PostMapping("getStudentsByParams")
    public List<Student> getStudentsByParams(@RequestParam String studentName,@RequestParam(required = false,defaultValue = "0") int cardId){
        return studentSevice.getStudentsByParams(studentName,cardId);
    }
}
/*
* SQL：Struts Query Languge
* HQL：Hibernate Query Languge
* JPQL：
* */