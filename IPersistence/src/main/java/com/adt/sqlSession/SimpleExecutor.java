package com.adt.sqlSession;

import com.adt.config.BoundSql;
import com.adt.pojo.Configuration;
import com.adt.pojo.MappedStatement;
import com.adt.utils.GenericTokenParser;
import com.adt.utils.ParameterMapping;
import com.adt.utils.ParameterMappingTokenHandler;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleExecutor implements Executor {
    @Override
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, IntrospectionException, InvocationTargetException, InstantiationException {
        //1.注册驱动 ,获取连接
        Connection connection = configuration.getDataSource().getConnection();
        //2.获取sql语句 select * from user where id=#{id} and username=#{username}
        //sql 不能被jdbc 识别 需转换sql语句 select * from user where id=? and username=? 转换换过程中,
        //还需要对#{}的值惊醒解析存储
        String sql = mappedStatement.getSql();
        BoundSql boundSql = getBoundSql(sql);
        //3.获取预处理对象 prepareStatement
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSqlText());
        //4.设置参数
        List<ParameterMapping> parameterMappingList = boundSql.getParameterMappingList();
        //获取参数的全路径
        String parameterType = mappedStatement.getParameterType();
        Class<?> parametClassType = getClassType(parameterType);
        for (int i = 0; i < parameterMappingList.size(); i++) {
            ParameterMapping parameterMapping = parameterMappingList.get(i);
            String content = parameterMapping.getContent();

            //反射
            Field declaredField = parametClassType.getDeclaredField(content);
            //防止private 设置 暴力访问
            declaredField.setAccessible(true);
            //得到参数名称的值
            Object o = declaredField.get(params[0]);//可变参 是个数组
            preparedStatement.setObject(i+1,o);
        }
        //5.执行sql
        ResultSet resultSet = preparedStatement.executeQuery();
        String resultType = mappedStatement.getResultType();
        Class<?> resultTypeClass = getClassType(resultType);
        //6.封装返回结果集
        ArrayList<Object> list = new ArrayList<>();
        while(resultSet.next()){
            Object o = resultTypeClass.newInstance();
            //获取元数据(包含字段的名称)
            ResultSetMetaData metaData = resultSet.getMetaData();
            for (int i = 1; i <=metaData.getColumnCount(); i++) {
                //字段名
                String columnName = metaData.getColumnName(i);
                //获取字段的值
                Object value = resultSet.getObject(columnName);
                //试用反射或者内省,根据数据库表和实体属性对应关系 完成封装
                //PropertyDescriptor是内省库中的类 根据对应关系生成读写方法
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultTypeClass);
                Method writeMethod = propertyDescriptor.getWriteMethod();
                writeMethod.invoke(o,value);
            }
            list.add(o);
        }
        return (List<E>) list;
    }

    private Class<?> getClassType(String parameterType) throws ClassNotFoundException {
        if(parameterType!=null){
            Class<?> aClass = Class.forName(parameterType);
            return aClass;
        }
        return  null;
    }

    /**
     * 完成对#{}的解析工作  1. 将#{}替换成?  2.将#{}的值进行存储
     *
     * @param sql
     * @return
     */
    private BoundSql getBoundSql(String sql) {
        //标记处理类:配置标记解析器来完成对占位符的解析处理工作
        ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", parameterMappingTokenHandler);
        //解析过后的sql
        String parseSql = genericTokenParser.parse(sql);
        //#{}里面解析出来的的参数名称
        List<ParameterMapping> parameterMappings = parameterMappingTokenHandler.getParameterMappings();
        BoundSql boundSql = new BoundSql(parseSql, parameterMappings);
        return boundSql;
    }
}
