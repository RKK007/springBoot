package com.sfac.java_spring_boot.models.test.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * @Description StudentCard
 * @Author HymanHu
 * @Date 2020/7/30 14:25
 *
 * 学生Card只与student形成一对一的关系。
 *
 */
@Entity//标识该类为实体类
@Table(name = "h_card")//把该类映射到数据库表，没有表会自动后创建
public class Card {
    @Id//标识该属性为表的主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键约束为自动增长
    private int cardId;


    @Column(name = "card_no", length = 33, unique = true)//将该属性映射为表的普通字段
    private String cardNo;



    /**
     * OneToOne：一对一关系中，多的一方使用 JoinColumn 注解（有外键），一的一方使用 mappedBy 属性来映射多的一方的 “本类” 属性（可选）
     * cascade：联级操作，refresh为级联刷新
     * fetch：加载数据策略，LAZY为懒加载，即用到Student的地方才加载，优化查询性能
     * JsonIgnore：不序列化该字段，避免无限递归
     */
    //单表的一边用mappedBy属性映射另一个实体类属性
    @OneToOne(mappedBy = "studentCard", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JsonIgnore
    private Student student;

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
