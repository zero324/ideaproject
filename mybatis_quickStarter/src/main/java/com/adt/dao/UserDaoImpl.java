package com.adt.dao;

import com.adt.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserDaoImpl {

    public List<User> findAll() throws IOException {
        //resources 工具类  加载配置文件  把配置文件加载成字节输入流
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //解析配置文件 创建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //生产sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();//openSession 默认开启一个事务,但不会自动提交
        //增删改的时候需要手动提交事务
        // 但是有一个有参数方法 openSession(true) 开启自动提交
        //sqlSession 调用方法   (sqlSession定义了与数据库交互的方法)
        List<User> users = sqlSession.selectList("user.findAll");
        return users;
    }
}
