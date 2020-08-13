package com.sfac.java_spring_boot.models.test.service.impl;


import com.sfac.java_spring_boot.models.common.vo.Result;
import com.sfac.java_spring_boot.models.common.vo.SearchVo;
import com.sfac.java_spring_boot.models.test.entity.Student;
import com.sfac.java_spring_boot.models.test.repository.StudentRepository;
import com.sfac.java_spring_boot.models.test.service.StudentSevice;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @Description StudentSeviceImpl
 * @Author HymanHu
 * @Date 2020/8/12 15:33
 */
@Service
public class StudentSeviceImpl implements StudentSevice {

    @Autowired
    private StudentRepository studentRepository;


/*=====================repository父类接口操作================================================================================================*/
    /**
     * 向学生表插入数据
     */
    @Override
    @Transactional
    public Result<Student> insertStudent(Student student) {
        student.setCreateDate(LocalDateTime.now());
        studentRepository.saveAndFlush(student);//保存并刷新数据
        return new Result<Student>(Result.ResultStatus.SUCCESS.status,
                "Insert success", student);
    }


    /**
     * 通过学生的Id来查询学生信息
     */
    @Override
    public Student findStudentById(int studentId) {
        return studentRepository.findById(studentId).get();
    }


    /**
     * findAll()                 ----不带条件查询
     * findAll(sort)             ----根据排序字段和排序方式查询
     * findAll(pageable)         ----根据分页条件查询
     * findAll(example,pageable) ----根据模糊查询模板和分页条件查询
     */
    @Override
    public Page<Student> getStudentBySearchVo(SearchVo searchVo) {
            //排序方式
            Sort.Direction direction = "desc".equalsIgnoreCase(searchVo.getSort()) ? Sort.Direction.DESC : Sort.Direction.ASC;

            //排序类（排序方式，排序字段）
            Sort sort = new Sort(direction,StringUtils.isBlank(searchVo.getOrderBy()) ? "studentId" : searchVo.getOrderBy());

            //获得分页对象（当前页，页大小，排序类），注意当前页要-1，因为插件默认0页为可视化的第一页
            Pageable pageable = PageRequest.of(searchVo.getCurrentPage() - 1,searchVo.getPageSize(),sort);

            //构造模糊查询模板
            Student student = new Student();
            student.setStudentName(searchVo.getKeyWord());//将关键字设置到student类
            ExampleMatcher matcher = ExampleMatcher.matching()
                    .withMatcher("studentName",match -> match.contains())
                    .withIgnorePaths("studentId");//不管ID是什么值都不参与查询
            Example<Student> example = Example.of(student,matcher);

            return studentRepository.findAll(example,pageable);
    }



/*=====================repository自定义方法操作================================================================================================*/
    /**
     *查询学生信息，通过学生姓名
     */
    @Override
    public List<Student> getStudentsByStudentName(String studentName) {
        return Optional.ofNullable(studentRepository.findByStudentName(studentName))
                .orElse(Collections.emptyList());
    }

    /**
     *查查询学生信息，通过学生姓名模糊查询
     */
    @Override
    public List<Student> getStudentsByStudentNameLike(String studentName) {
        return Optional.ofNullable(studentRepository.findByStudentNameLike(String.format("%s%S%s","%",studentName,"%")))
                .orElse(Collections.emptyList());
    }

    /**
     *查询学生信息，通过学生姓名模糊查询，显示前两条数据
     */
    @Override
    public List<Student> getTop2StudentsByStudentNameLike(String studentName) {
        return Optional.ofNullable(studentRepository.findTop2ByStudentNameLike(String.format("%s%S%s","%",studentName,"%")))
                .orElse(Collections.emptyList());
    }




/*=====================repository层@Query注解查询操作================================================================================================*/
    @Override
    public List<Student> getStudentsByParams(String studentName, int cardId) {
        if(cardId>0){
            return studentRepository.getStudentsByParams(studentName,cardId);
        }else {
            return Optional.ofNullable(studentRepository.findTop2ByStudentNameLike(String.format("%s%S%s","%",studentName,"%")))
                    .orElse(Collections.emptyList());
        }
    }
}