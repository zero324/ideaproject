package com.edu.pojo;

import javax.persistence.*;

/*
 *简历实体类(在类中使用注解建立实体类与数据表之间的映射关系以及属性和字段的映射关系)
 *   1 实体类与映射表映射关系
 *   @Entity @Table
 *   2 实体类属性与表字段之间的映射关系
 *   @Id
 *   @GeneratedValue
 *   @Column
 * */
@Entity
@Table(name="tb_resume")
public class Resume {
    /*
     * 生成策略经常使用的两种
     * GenerationType.IDENTITY :依赖数据库主键自增的功能 Mysql
     * GenerationType.SEQUENCE :依赖数序列来产生主键     Oracle
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
