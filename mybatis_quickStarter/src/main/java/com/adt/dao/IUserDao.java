package com.adt.dao;

import com.adt.pojo.User;

import java.io.IOException;
import java.util.List;

public interface IUserDao {
    //查询所有
    List<User> findAll() throws IOException;

    //多条件组合查询
    List<User> findByCondition(User user);

    //多值查询
    List<User> findByIds(int[] ids);
}
