package com.edu.springboot_thymeleaf;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;

@SpringBootTest
class SpringbootThymeleafApplicationTests {
    @Autowired
    private LocaleResolver locale;

    @Test
    void contextLoads() {
        System.out.println(locale);
    }
    @Test
    public void testXXXX(){
        System.out.println("哈哈哈哈哈");
    }
}
