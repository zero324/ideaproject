package com.adt.test;

import com.adt.dao.IUserDao;
import com.adt.io.Resources;
import com.adt.pojo.User;
import com.adt.sqlSession.SqlSession;
import com.adt.sqlSession.SqlSessionFactory;
import com.adt.sqlSession.SqlSessionFactoryBuilder;
import org.dom4j.DocumentException;

import java.beans.IntrospectionException;
import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class IPersistenceTest {
    public static void main(String[] args) throws PropertyVetoException, DocumentException, IllegalAccessException, IntrospectionException, InstantiationException, NoSuchFieldException, SQLException, InvocationTargetException, ClassNotFoundException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

     /*   //调用
        User user = new User();
        user.setId(2);
        user.setUsername("张三");

       Object o = sqlSession.selectOne("user.selectOne",user);
        System.out.println(o.toString());*/
       /* List<User> objects = sqlSession.selectList("user.selectList");
        for (User object : objects) {
            System.out.println(object.toString());
        }*/
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        //代理对象调用接口中任意方法,都会执行invoke方法
        List<User> all = userDao.findAll();
        for (User object : all) {
            System.out.println(object.toString());
        }
    }
}
