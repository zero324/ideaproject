package com.adt.dao;

import com.adt.pojo.Order;
import com.adt.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
@CacheNamespace//注解开发 开启二级缓存
public interface IOrderMapper {

    @Results({
            @Result(property = "id",column ="id"),
            @Result(property = "ordertime",column ="ordertime"),
            @Result(property = "total",column ="total"),
            @Result(property = "user",column ="uid",javaType =User.class,one = @One(select = "com.adt.dao.IOrderMapper.selectUserById")),
    })
    @Select("select * from orders")
    List<Order> findOrderAndUser();

    @Results({
            @Result(property = "id",column = "id" ),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "birthday",column = "birthday"),
            @Result(property = "orderList", javaType =List.class ,column = "id", many = @Many(select ="com.adt.dao.IOrderMapper.selectOrders" )),
    })//javaType 写不写都行
    @Select("select * from user")
    List<User> findUserAndOrder();

    //增加用户
    @Insert("insert into user values(#{id},#{username},#{password},#{birthday})")
    void saveUser(User user);

    //修改用户
    @Update("update user set username=#{username} where id=#{id}")
    void updateUser(User user);

    //查询所有
    @Select("select * from user")
    List<User> selectUsers();

    //删除用户
    @Delete("delete from user where id=#{aas}")
    void deleteUser(Integer id);

    @Select("select * from user where id=#{id}")
    User selectUserById(Integer id);

    @Select("select * from orders where uid=#{id}")
    List<Order> selectOrders(Integer id);
}
