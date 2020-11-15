package com.adt.demo.controller;

import com.adt.demo.service.IDemoService;
import com.adt.edu.mvcframework.annotations.AdtAutoWired;
import com.adt.edu.mvcframework.annotations.AdtController;
import com.adt.edu.mvcframework.annotations.AdtRequestMapping;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@AdtController
@AdtRequestMapping("/demo")
public class DemoController {
    @AdtAutoWired
    private IDemoService demoService;

    public  String query(HttpServletRequest request, HttpServletResponse response, String name){
        return demoService.get(name);
    }
}
