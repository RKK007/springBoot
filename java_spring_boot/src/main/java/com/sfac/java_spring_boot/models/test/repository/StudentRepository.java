package com.sfac.java_spring_boot.models.test.repository;

import com.sfac.java_spring_boot.models.test.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description StudentRepository
 * @Author HymanHu
 * @Date 2020/8/12 15:31
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {


/*=====================repository层自定义方法操作================================================================================================*/
    List<Student> findByStudentName(String studentName);

    List<Student> findByStudentNameLike(String studentName);

    List<Student> findTop2ByStudentNameLike(String studentName);





/*=====================repository层@Query注解查询操作================================================================================================*/
//  @Query(value = "select s from Student s where s.studentName = ?1 and s.studentCard.cardId = ?2")    //第一种问号参数化查询
//    List<Student> getStudentsByParams(String studentName, int cardId);

//  @Query(value = "select s from Student s where s.studentName = :studentName and s.studentCard.cardId = :cardId")//第二种冒号参数化查询
//    List<Student> getStudentsByParams(String studentName, int cardId);


    //    nativeQuery是否开启原生的SQL语句
    @Query(nativeQuery = true,value = "select * from h_student where student_name = :studentName and card_id = :cardId")//第三种原生的SQL实现
    List<Student> getStudentsByParams(@Param("studentName") String studentName, @Param("cardId") int cardId);

}














