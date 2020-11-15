package com.adt.edu.mvcframework.servlet;

import com.adt.edu.mvcframework.annotations.AdtController;
import com.adt.edu.mvcframework.annotations.AdtService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class AdtDispacherServlet extends HttpServlet {

    private Properties properties;

    //缓存扫描到的全限定类名
    private List<String> classNames=new ArrayList<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        //1 加载配置文件 springmvc.properties
        String contextConfigLocation = config.getInitParameter("contextConfigLocation");
        doLoadCofig(contextConfigLocation);

        //2 扫描相关的类 ,扫描注解
        doScan("");
        //3 初始化Bean对象 (初始化ioc容器,基于注解)
        try {
            doInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //4  实现依赖注入
        doAutoWired();
        //5构建一个HandlerMapping处理器映射器 将url和方法建立映射关系
        initHandlerMapping();
        System.out.println("adt mvc 初始化完成........");
        //6 请求进入 处理请求
    }

    //构建一个HandlerMapping处理器映射器
    private void initHandlerMapping() {

    }

    //实现依赖注入
    private void doAutoWired() {
    }

    //ioc容器
    //基于classNames的 类的全限定类名 以及反射技术,完成对象的创建和管理
    private void doInstance() throws ClassNotFoundException {
        if(classNames.size()==0) return;

        for (int i = 0; i <classNames.size() ; i++) {
            String className = classNames.get(i);
            //反射
            Class<?> aClass = Class.forName(className);
            //区分controller, 区分service
            if(aClass.isAnnotationPresent(AdtController.class)){
                //controller不做过多处理, 不取value了 就拿类的小写首字母作为id,保存到ioc容器中
            }else if(aClass.isAnnotationPresent(AdtService.class)){

            }
        }
    }



    //扫描类
    //scanPackage : com.adt.edu  --->磁盘上文件(File 能判断是否有子文件夹)  这是个递归的过程  包不能判断有没有子包
    private void doScan(String scanPackage) {
      //  Thread.currentThread().getContextClassLoader().getResource("").getPath() 得到的路径是classpath的路径
        //scanPackagePath 磁盘上的路径
        String scanPackagePath = Thread.currentThread().getContextClassLoader().getResource("").getPath() + scanPackage.replaceAll("//.", "/");
        File pack = new File(scanPackagePath);
        File[] files = pack.listFiles();
        for (File file:files) {
            if (file.isDirectory()){
                //递归
                doScan(scanPackagePath+"."+file.getName());//com.adt.demo.controller
            }else if(file.getName().endsWith(".class")){
                String className = scanPackagePath + "." + file.getName().replaceAll(".class", "");
                classNames.add(className);
            }


        }


    }

    //加载配置文件
    private void doLoadCofig(String contextConfigLocation) {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODU 业务
    }


}
