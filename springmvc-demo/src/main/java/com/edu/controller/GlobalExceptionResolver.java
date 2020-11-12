package com.edu.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//可以让我们捕获所有的contrller对象handler方法的异常
@ControllerAdvice
public class GlobalExceptionResolver {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception e, HttpServletResponse response) {   //返回值可有可无  可以返回任意类型  也可以返回 ModelAndView
        //异常处理逻辑
        ModelAndView modelAndView = new ModelAndView();
       /* try {
            response.getWriter().write("===>"+e.getMessage());*/
            modelAndView.setViewName("error");
            modelAndView.addObject("msg",e.getMessage());
        /*} catch (IOException ex) {
            ex.printStackTrace();
        }*/
        return modelAndView;
    }
}
