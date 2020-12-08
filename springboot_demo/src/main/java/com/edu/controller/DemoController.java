package com.edu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("demo")
public class DemoController {

    @RequestMapping("hello")
    public String helloWord(){
        return "你好 Hello World  热部署";
    }
}
