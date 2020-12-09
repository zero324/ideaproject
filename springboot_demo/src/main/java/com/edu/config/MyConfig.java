package com.edu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration//定义该类是一个配置类
public class MyConfig {

    @Bean//将返回值对象作为组件添加到spring ioc容器中   id默认为方法名
    public MyService myService() {
        return new MyService();
    }

}
