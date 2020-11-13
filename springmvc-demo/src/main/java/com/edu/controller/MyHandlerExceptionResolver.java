package com.edu.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//@Component/*与@ControllerAdvice  @ExceptionHandler 同时存在 HandlerExceptionResolver 失效 */
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        System.out.println("======>HandlerExceptionResolver<=============="+e.getMessage());
        ModelAndView  modelAndView=new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("msg",e.getMessage());
        return modelAndView;
    }
}
