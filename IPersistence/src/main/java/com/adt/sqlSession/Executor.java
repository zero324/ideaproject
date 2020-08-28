package com.adt.sqlSession;

import com.adt.pojo.Configuration;
import com.adt.pojo.MappedStatement;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface Executor {
   public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement,Object ... params) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, IntrospectionException, InvocationTargetException, InstantiationException;
}
