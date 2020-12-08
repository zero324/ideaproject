package com.edu.springboot_demo;

import com.edu.controller.DemoController;
import com.edu.SpringbootDemoApplication;
import com.edu.pojo.Person;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)  //测试启动类   并加载测试注解
@SpringBootTest(classes = SpringbootDemoApplication.class)
        //标记springboot 单元测试类 并加载applicationContext 上下文环境
class SpringbootDemoApplicationTests {
    @Autowired
    private DemoController demoController;
    @Autowired
    private Person person;


    @Test
        //自动创建单元测试方法实例
    void contextLoads() {
        // String demo = demoController.helloWord();
        System.out.println(person);
    }


}
