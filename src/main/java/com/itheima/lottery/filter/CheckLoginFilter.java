package com.itheima.lottery.filter;

import com.itheima.lottery.bean.User;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// @ServletComponentScan
/*
    若想使用webfilter 需要添加 @ServletComponentScan
*/
//@Component
@WebFilter(urlPatterns = {"/note/publish","/comment/add","/user/userUI"})
public class CheckLoginFilter implements Filter {
    @Override
    //执行过滤的方法
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //判断用户是否登录
        User user = (User) request.getSession().getAttribute("user");
        System.out.println("执行了过滤器....");
        if(user == null){
            //获取用户想要访问的路径
            String requestURI = request.getRequestURI();
            request.getSession().setAttribute("loginPreUri",requestURI);
            System.out.println("用户想要访问的路径："+requestURI);

            //跳转去登录页面 : 重定向
            response.sendRedirect("/user/loginUI");
            return ;
        }else{
            //放行
            chain.doFilter(request,response);
        }
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }


    @Override
    public void destroy() {

    }
}
