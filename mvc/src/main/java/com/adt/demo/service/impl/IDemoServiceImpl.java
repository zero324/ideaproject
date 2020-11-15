package com.adt.demo.service.impl;

import com.adt.demo.service.IDemoService;
import com.adt.edu.mvcframework.annotations.AdtService;

@AdtService
public class IDemoServiceImpl implements IDemoService {
    @Override
    public String get(String name) {
        System.out.println("service 实现类中的name参数: "+name);
        return name;
    }
}
