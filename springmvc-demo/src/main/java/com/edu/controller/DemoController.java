package com.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;

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

    /**
     * SpringMVC 在handle方法上传入 ModelMap  Model   Map 并向这些参数保存数据(放入请求域中)
     *
     * 它们之间什么关系?
     *ModelMap===class org.springframework.validation.support.BindingAwareModelMap
     *运行时具体类型都是BindingAwareModelMap,相当于给BindingAwareModelMap保存的数据都保存在请求域中
     * Map(jdk 解救) Model (spring  接口) ModelMap(map接口实现)
     * class BindingAwareModelMap extends ExtendedModelMap
     * ExtendedModelMap extends ModelMap implements Model
     **/

    @RequestMapping("handler11")
    public String handler11(ModelMap map) {
        Date date = new Date();
//        map.put("data", date);
        System.out.println("ModelMap==============="+map.getClass());
        map.addAttribute("data", date);
        return "success";
    }

    @RequestMapping("handler12")
    public String handler12(Model map) {
        Date date = new Date();
        System.out.println("Model==============="+map.getClass());
        map.addAttribute("data", date);
        return "success";
    }

    @RequestMapping("handler13")
    public String handler13(HashMap<String,Object> map) {
        Date date = new Date();
        System.out.println("HashMap==============="+map.getClass());
        map.put("data", date);
        return "success";
    }
    @RequestMapping("handler14")
    public String handler14(HashMap<String,Object> map,Date birthday) {
        Date date = new Date();
        System.out.println("HashMap==============="+map.getClass());
        map.put("data", date);
        return "success";
    }

    /**
     * 默认⽀持 Servlet API 作为⽅法参数
     * 当需要使⽤HttpServletRequest、HttpServletResponse、HttpSession等原⽣servlet对象时，直
     * 接在handler⽅法中形参声明使⽤即可
     *
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
    public void upload(MultipartFile uploadName) {
       //处理上传文件
        //重命名
        String originalFilename = uploadName.getOriginalFilename();
        //拓展名jpg
        String ext = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
    }
}
