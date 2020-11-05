package com.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping("handler01")
    public ModelAndView handler(){
        Date date = new Date();
        ModelAndView modelAndView = new ModelAndView();
        //设置数据模型model 相当于 request.setAttribute()
        modelAndView.addObject("data",date);
        //设置视图   web-info里的页面浏览器不能直接访问  逻辑视图名success  物理视图名/WEB-INF/jsp/逻辑视图名success.jsp
        modelAndView.setViewName("success");
        return modelAndView;
    }
}
