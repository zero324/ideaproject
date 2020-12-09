package com.edu.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration    // 自定义配置类
@PropertySource("classpath:test.properties")//指定自定义配置文件名称 位置
@EnableConfigurationProperties(MyProperties.class)//开启指定配置文件的属性注入功能
@ConfigurationProperties(prefix = "test") //指定配置文件属性前缀
public class MyProperties {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyProperties{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
