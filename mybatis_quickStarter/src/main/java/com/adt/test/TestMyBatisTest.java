package com.adt.test;

import com.adt.dao.IUserDao;
import com.adt.dao.UserDaoImpl;
import com.adt.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TestMyBatisTest {
    @Test
    public void test() throws IOException {
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
        for (User user : users) {
            System.out.println(user);
        }
        sqlSession.close();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        atomicInteger.incrementAndGet();
        Calendar c = Calendar.getInstance();
        //c.set(Calendar.MONTH,0);
        c.set(Calendar.DAY_OF_MONTH, 1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(simpleDateFormat.format(c.getTime()));
    }

    @Test
    public void test2() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setId(2);
        user.setUsername("nike");
        user.setPassword("123456");
        user.setBirthday("2012-01-01");
        int insert = sqlSession.insert("com.adt.dao.IUserDao.saveUser", user);
        //提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test3() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setId(3);
        user.setUsername("lucy修改");
        user.setPassword("哈哈");
        user.setBirthday("2022-01-01");
        int update = sqlSession.update("user.updateUser", user);
        //提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test4() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
       /* User user = new User();
        user.setId(3);
        user.setUsername("lucy修改");
        user.setPassword("哈哈");
        user.setBirthday("2022-01-01");*/
        int update = sqlSession.update("com.adt.dao.IUserDao.deleteUser", 3);
        //提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test5() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        List<User> users = mapper.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void test6() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        int[] arr={1,2};
        List<User> users = mapper.findByIds(arr);
        for (User user : users) {
            System.out.println(user);
        }
    }
}
