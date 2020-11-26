package com.edu.controller;

import com.edu.pojo.User;
import com.edu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    /**
     * spring 容器和springmvc容器是有层次的(父子容器)
     * spring ioc容器: service对象+到对象
     * springmvc容器 : cpntroller 对象  ... 可以引用spring ioc容器的对象
     */
    @Autowired
    private IUserService userService;

    @RequestMapping("/queryAll")
    @ResponseBody
    private List<User> queryAll() throws Exception {
        return userService.selectList();
    }

}
