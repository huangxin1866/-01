package com.itheima.lottery.controller;

import com.aliyuncs.exceptions.ClientException;
import com.itheima.lottery.bean.User;
import com.itheima.lottery.service.UserService;
import com.itheima.lottery.utils.BallUtils;
import com.itheima.lottery.utils.SMSUtils;
import com.itheima.lottery.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/*
*  注册案例
*    提交数据去保存
*    使用阿里云发送验证码
*    md5加密
*    UUID : 生成唯一的标识符
*  登录案例
*     查询数据
*     自定义email和password去查询
*     md5加密
*
*  我的信息页面展示
* */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // User Interface  /user/registerUI
    @GetMapping("/registerUI")
    public String registerUI(Model model){
        model.addAttribute("user",new User());
        return "register";
    }
    //   /user/register
    @PostMapping("/register")
    public String register(User user,String code,HttpSession session,Model model){
        //校验session中验证码和用户的验证码是否一致
        String sessionCode = (String) session.getAttribute("code");

        if (!code.equals(sessionCode)){
            model.addAttribute("msg","验证码不匹配.");
            model.addAttribute("user",user);
            return "register";
        }

        //1.接收参数
            // 填充user对象中剩余的参数
        user.setHeadimg("/img/icon.jpg");
        user.setMoney(100);
        //用户的状态:0 未激活, 1,已激活, 2: 已封号
        user.setState(1);
        user.setUid(UUIDUtils.getId());

        //2.调用业务
        userService.doRegister(user);

        //3.跳转登录响应
        return "redirect:/user/loginUI";
    }
    //访问用户登录页面
    @GetMapping("/loginUI")
    public String loginUI(Model model){


        return "login";
    }


    //登录相关的业务  /user/login
    @PostMapping("/login")
    public String login(String email,String password,Model model,HttpSession session){
        //调用业务查询用户
        User user = userService.doLogin(email,password);

        //判断user是否存在
        if(user != null){
            //保存用户登录的状态
            session.setAttribute("user",user);

            // 获取登录之前想要的页面跳转
            String loginPreUri = (String) session.getAttribute("loginPreUri");
            if(loginPreUri != null){
                return "redirect:"+loginPreUri;
            }
            //跳转去首页
            return "redirect:/";
        }
        //走到这里证明登录失败
        model.addAttribute("msg","用户名或密码错误");
        return "login";
    }


    //显示个人主页内容
    @GetMapping("/userUI")
    public String userUI(Model model){

        List<String> redBalls = BallUtils.randomRed();
        String blue = BallUtils.randomBlue();

        model.addAttribute("redBalls",redBalls);
        model.addAttribute("blue",blue);

        return "my";
    }



    @GetMapping("/sendSMS")
    @ResponseBody
    public String sendSMS(String mobile, HttpSession session) throws ClientException {
        //发送一个验证码
        String code = SMSUtils.sendSms(mobile);
        //将当前生成的验证码保存到session中
        session.setAttribute("code",code);
        System.out.println("成功保存了code:"+code);
        return "success";
    }

    @GetMapping("/touxiangUI")
    public String touxiangUI(){

        return "touxiang";
    }


}
