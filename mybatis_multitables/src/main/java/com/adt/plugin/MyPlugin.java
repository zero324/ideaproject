package com.adt.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.util.Properties;

@Intercepts({//可以配置多个@Signature
        @Signature(type= StatementHandler.class,method="prepare",args ={Connection.class,Integer.class} )
})
public class MyPlugin implements Interceptor {
    /**
     * 拦截方法:只要被拦截的目标对象的目标方法被执行时,每次都会执行intercept
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("对方法进行了增强.......");
        return invocation.proceed();//返回执行原方法
    }

    /**
     * 主要是把当前的拦截器生成代理对象存到拦截器链中
     * @param target 被拦截的对象
     * @return
     */
    @Override
    public Object plugin(Object target) {
        Object wrap = Plugin.wrap(target, this);
        System.out.println("主要是把当前的拦截器生成代理对象存到拦截器链中");
        return wrap;
    }

    /**
     * 获取配置文件的参数的
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {
        System.out.println("获取到的配置文件的参数: "+properties);
    }
}
