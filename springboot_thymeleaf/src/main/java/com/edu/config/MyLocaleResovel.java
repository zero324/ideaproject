package com.edu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Configuration("localeResolver")
public class MyLocaleResovel implements LocaleResolver {
    //自定义区域解析方式
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        //获取页面手动切换传递的语言参数1
        String l = request.getParameter("l");
        //获取页面手动切换传递的语言参数Accept-Language
        String header = request.getHeader("Accept-Language");
        Locale locale = null;
        //如果手动切换参数不为空,就根据手动切换参数切换,否则根据请求头信息切换
        if (!StringUtils.isEmpty(l)) {
            String[] split = l.split("_");
            locale = new Locale(split[0], split[1]);
        } else {
            // Accept-Language: en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7
             String[] splits = header.split(",");
             String[] split = splits[0].split("-");
             locale=new Locale(split[0],split[1]);        }
        return locale;
        }

        @Override
        public void setLocale (HttpServletRequest request, HttpServletResponse response, Locale locale){

        }
   /* //将MyLocaleResovel注册为LocaleResolver bean组件
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResovel();
    }*/
}
