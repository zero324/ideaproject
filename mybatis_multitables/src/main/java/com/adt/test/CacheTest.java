package com.adt.test;

import com.adt.dao.IOrderMapper;
import com.adt.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class CacheTest {
    private IOrderMapper mapper;
    private SqlSession sqlSession;
    private SqlSessionFactory sqlSessionFactory;
    @Before
    public void before() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
         sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        sqlSession = sqlSessionFactory.openSession();//true 自动提交事务
        mapper = sqlSession.getMapper(IOrderMapper.class);
    }
    @Test
    public void firstlevelCache() throws IOException {
        User user1 = mapper.selectUserById(1);
        User user = new User();
        user.setId(1);
        user.setUsername("修改");
        mapper.updateUser(user);
        sqlSession.commit();//commit  或clearChache()  都会清空 sqlSession 一级缓存
        User user2 = mapper.selectUserById(1);
        System.out.println(user1==user2);
    }
    @Test
    public void secondCache(){
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        SqlSession sqlSession3 = sqlSessionFactory.openSession();
        IOrderMapper mapper1 = sqlSession1.getMapper(IOrderMapper.class);
        IOrderMapper mapper2 = sqlSession2.getMapper(IOrderMapper.class);
        IOrderMapper mapper3 = sqlSession3.getMapper(IOrderMapper.class);
        User user1 = mapper1.selectUserById(1);
        sqlSession1.close();//关闭sqlSession  缓存也就没了  并且更新二级缓存


        User user = new User();
        user.setId(1);
        user.setUsername("修改");
        mapper3.updateUser(user);
        sqlSession3.commit();  //增加 删除  修改 commit之后 清空二级缓存

        User user2 = mapper2.selectUserById(1);
        System.out.println(user1==user2);//false  二级缓存 保存的是数据new Object  user必须实现序列化接口
    }
}
