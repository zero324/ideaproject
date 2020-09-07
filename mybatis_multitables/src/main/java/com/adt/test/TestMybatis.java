package com.adt.test;

import com.adt.dao.IOrderMapper;
import com.adt.dao.UserMapper;
import com.adt.pojo.Order;
import com.adt.pojo.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class TestMybatis {
    @Test
    public void test1() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IOrderMapper mapper = sqlSession.getMapper(IOrderMapper.class);
        List<Order> orderAndUser = mapper.findOrderAndUser();
        for (Order order : orderAndUser) {
            System.out.println(order);
        }
    }
    @Test
    public void test2() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IOrderMapper mapper = sqlSession.getMapper(IOrderMapper.class);
        List<User> userAndOrder = mapper.findUserAndOrder();
        for (User user : userAndOrder) {
            System.out.println(user);
        }
    }
    private   IOrderMapper mapper;
    @Before
    public void before() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
         mapper = sqlSession.getMapper(IOrderMapper.class);
    }
    @Test
    public void addUser() throws IOException {
        User user1 = new User();
        user1.setId(3);
        user1.setUsername("ford");
        user1.setPassword("123456");
        user1.setBirthday("2020-01-01");
        mapper.saveUser(user1);
//        for (User user : users) {
//            System.out.println(user);
//        }
    }
    @Test
    public void updateUser() throws IOException {
        User user1 = new User();
        user1.setId(3);
        user1.setUsername("ford tom");
        user1.setPassword("123456");
        user1.setBirthday("2020-01-01");
        mapper.updateUser(user1);
//        for (User user : users) {
//            System.out.println(user);
//        }
    }

    @Test
    public void deleteUser() throws IOException {

        mapper.deleteUser(3);
//        for (User user : users) {
//            System.out.println(user);
//        }
    }
    @Test
    public void oneToOne() throws IOException {

        List<Order> orderAndUser = mapper.findOrderAndUser();
        for (Order order : orderAndUser) {
            System.out.println(order);
        }
    }

    @Test
    public void oneToMany() throws IOException {

        List<User> userAndOrder = mapper.findUserAndOrder();
        for (User user : userAndOrder) {
            System.out.println(user);
        }
    }
    @Test
    public void TestPageHelper() throws IOException {
        PageHelper.startPage(1,1);
        List<User> users = mapper.selectUsers();
        for (User user : users) {
            System.out.println(user);
        }
        PageInfo<User> userPageInfo = new PageInfo<>(users);
        System.out.println("当前页"+userPageInfo.getPageNum());
        System.out.println("总页数"+userPageInfo.getPages());
        System.out.println("一页多少条"+userPageInfo.getPageSize());
        System.out.println("数据总数"+userPageInfo.getTotal());
    }

    @Test
    public void TestMapper() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setId(1);
        User user1 = mapper.selectOne(user);
        System.out.println(user1);

        //example方法
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("id",2);
        List<User> users = mapper.selectByExample(example);
        for (User user2 : users) {
            System.out.println(user2);
        }
    }

}
