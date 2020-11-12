package com.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

@Controller
@RequestMapping("/demo")
public class DemoController {


    //springmvc的异常处理机制(异常处理器)
    //注意写在当前类里只对当前controller有效
   /* @ExceptionHandler(Exception.class)
    public void handleException(Exception e,HttpServletResponse response){   //返回值可有可无  可以返回任意类型  也可以返回 ModelAndView
        //异常处理逻辑
        try {
            response.getWriter().write(e.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }*/


    @RequestMapping("handler01")
    public ModelAndView handler(@ModelAttribute("name") String name) {
        int i = 1 / 0;
        Date date = new Date();
        ModelAndView modelAndView = new ModelAndView();
        //设置数据模型model 相当于 request.setAttribute()
        modelAndView.addObject("data", date);
        //设置视图   web-info里的页面浏览器不能直接访问  逻辑视图名success  物理视图名/WEB-INF/jsp/逻辑视图名success.jsp
        modelAndView.setViewName("success");
        return modelAndView;
    }

    /**
     * SpringMVC 在handle方法上传入 ModelMap  Model   Map 并向这些参数保存数据(放入请求域中)
     * <p>
     * 它们之间什么关系?
     * ModelMap===class org.springframework.validation.support.BindingAwareModelMap
     * 运行时具体类型都是BindingAwareModelMap,相当于给BindingAwareModelMap保存的数据都保存在请求域中
     * Map(jdk 解救) Model (spring  接口) ModelMap(map接口实现)
     * class BindingAwareModelMap extends ExtendedModelMap
     * ExtendedModelMap extends ModelMap implements Model
     **/

    @RequestMapping("handler11")
    public String handler11(ModelMap map) {
        Date date = new Date();
//        map.put("data", date);
        System.out.println("ModelMap===============" + map.getClass());
        map.addAttribute("data", date);
        return "success";
    }

    @RequestMapping("handler12")
    public String handler12(Model map) {
        Date date = new Date();
        System.out.println("Model===============" + map.getClass());
        map.addAttribute("data", date);
        return "success";
    }

    @RequestMapping("handler13")
    public String handler13(HashMap<String, Object> map) {
        Date date = new Date();
        System.out.println("HashMap===============" + map.getClass());
        map.put("data", date);
        return "success";
    }

    @RequestMapping("handler14")
    public String handler14(HashMap<String, Object> map, Date birthday) {
        Date date = new Date();
        System.out.println("HashMap===============" + map.getClass());
        map.put("data", date);
        return "success";
    }

    /**
     * 默认⽀持 Servlet API 作为⽅法参数
     * 当需要使⽤HttpServletRequest、HttpServletResponse、HttpSession等原⽣servlet对象时，直
     * 接在handler⽅法中形参声明使⽤即可
     */
    @RequestMapping("handler15")
    public String handler15(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        Date date = new Date();
        ModelMap map = new ModelMap();
        System.out.println("HashMap===============" + map.getClass());
        map.put("data", date);
        return "success";
    }

    @RequestMapping("upload")
    public String upload(MultipartFile uploadName, HttpSession session) throws IOException {
        //处理上传文件
        //重命名
        String originalFilename = uploadName.getOriginalFilename();
        //拓展名jpg
        String ext = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String newName = UUID.randomUUID().toString() + "." + ext;

        //存储 要存储到指定的文件夹 /upload 考虑文件过多情况 (一个文件夹存储文件的数量是有限的) 创建子目录
        String realPath = session.getServletContext().getRealPath("/uploads");
        String datePath = new SimpleDateFormat("yyyyy-MM-dd").format(new Date());
        File folder = new File(realPath + "/" + datePath);
        if (!folder.exists()) {
            folder.mkdir();
        }
        uploadName.transferTo(new File(folder, newName));
        //TODU 将磁盘文件路径更新到数据库
        return "success";
    }

    /**
     * springmvc 重定向参数传递问题
     * 内部转 : 一个请求   url不变 携带参数
     * 重定向: 两个请求   url变   参数丢失需重新携带参数
     */

    @RequestMapping("/handlerRedirect")
    public String handlerRedirect(String name, RedirectAttributes redirectAttributes ) {
        //注意:冒号之后不能有空格
        //  return "redirect:handler01?name="+name;  get请求有局限  数据小   安全性差

        //redirectAttributes.addAttribute() 这种与get请求是一样的
        //可以使用flash属性
        //addFlashAttribute 设置了一个flash属性 ,该属性会被暂存到session中,在跳转之页面后会销毁
        redirectAttributes.addFlashAttribute("name",redirectAttributes);
        return "redirect:handler01";
    }
}
