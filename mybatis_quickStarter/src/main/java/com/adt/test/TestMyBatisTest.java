package com.adt.test;

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
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
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
        user.setId(3);
        user.setUsername("lucy");
        user.setPassword("123456");
        user.setBirthday("2012-01-01");
        int insert = sqlSession.insert("user.saveUser", user);
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
        int update = sqlSession.update("user.deleteUser", 3);
        //提交事务
        sqlSession.commit();
        sqlSession.close();
    }
}
