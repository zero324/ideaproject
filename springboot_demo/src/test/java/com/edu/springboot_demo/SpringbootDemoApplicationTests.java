package com.edu.springboot_demo;

import com.edu.controller.DemoController;
import com.edu.SpringbootDemoApplication;
import com.edu.pojo.MyProperties;
import com.edu.pojo.Person;
import com.edu.pojo.Student;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)  //测试启动类   并加载测试注解
@SpringBootTest
//(classes = SpringbootDemoApplication.class)
        //标记springboot 单元测试类 并加载applicationContext 上下文环境
class SpringbootDemoApplicationTests {
    @Autowired
    private DemoController demoController;
    @Autowired
    private Person person;
    @Autowired
    private Student student;
    @Autowired
    private MyProperties myProperties;
    @Autowired
    private ApplicationContext applicationContext;
    @Value("${tom.description}")
    private String description;

    @Test
        //自动创建单元测试方法实例
    void contextLoads() {
        // String demo = demoController.helloWord();
        System.out.println(applicationContext.containsBean("myService"));
    }

    @Test
    public void placeholderTest() {
        System.out.println(description);
    }


}
