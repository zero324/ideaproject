package com.adt.sqlSession;


import com.adt.config.XMLConfigBuilder;
import com.adt.pojo.Configuration;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;

public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(InputStream inputStream) throws PropertyVetoException, DocumentException {
        //1 使用dom4j解析配置文件 ,将解析出来的内容封装到Configuration核心配置类中
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder();
        Configuration configuration = xmlConfigBuilder.parseConfig(inputStream);
        //2 创建sqlSessionFactory 对象:工厂类  生产sqlSession对象:会话对象
        SqlSessionFactory defaultSqlSessionFactory = new DefaultSqlSessionFactory(configuration);
        return defaultSqlSessionFactory;
    }
}
