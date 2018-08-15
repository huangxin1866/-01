package com.itheima.thymeleaf.controller;

import com.itheima.thymeleaf.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class DemoController {

    //简单的入门案例
    @GetMapping("/demo1")
    public String demo1(Model model){

        model.addAttribute("msg","hello from Springboot");
        model.addAttribute("className","c2");
        return "demo1";
    }

    @GetMapping("/demo2")
    public String demo2(){
        System.out.println("被访问了");
        return "demo2";
    }
//基本语法 , if 条件判断 , 循环处理
    @GetMapping("/demo3")
    public String demo3(){
        System.out.println("被访问了");
        return "demo3";
    }
    @GetMapping("/demo4")
    public String demo4(){
        System.out.println("被访问了");
        return "demo4";
    }

    @GetMapping("/demo5")
    public String demo5(Model model){
        model.addAttribute("i",11);

        model.addAttribute("city","深圳");
        return "demo5";
    }

    @GetMapping("/demo6")
    public String demo6(Model model){
        ArrayList<String> cities = new ArrayList<String>();
        cities.add("深圳");
        cities.add("东莞");
        cities.add("广州");
        cities.add("惠州");

        model.addAttribute("name","四四就四四");
        model.addAttribute("cities",cities);
        return "demo6";
    }

    @GetMapping("/demo7")
    public String demo7(Model model){
        System.out.println("demo7 sout...7777");

        User user = new User();
        user.setUsername("张三");
        user.setSex("妖");
        user.getSex();


        System.out.println(user);

        return "demo7";
    }
}
