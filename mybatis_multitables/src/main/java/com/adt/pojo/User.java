package com.adt.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
@Table(name="user")// 通用mapper 配置
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//数据支持的主键生产策略    oracle用序列
    private Integer id;
    //如果属性与表中字段不一样的 用@Column
    private String username;
    private String password;
    private String birthday;
    //用户关联的订单
    @Transient //忽略
    private List<Order> orderList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }



    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
