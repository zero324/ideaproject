package com.edu.test;

import com.edu.pojo.User;
import com.edu.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:application*.xml"})
public class MybatisSpringTest {
    // 希望测试ioc容器中的哪个对象 注入即可
     @Autowired
     private IUserService userService;
    @Test
    public void testMybatisSpring() throws Exception {
        List<User> users = userService.selectList();
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            System.out.println(user);
        }
    }
}

