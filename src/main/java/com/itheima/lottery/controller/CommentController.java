package com.itheima.lottery.controller;

import com.itheima.lottery.bean.Comment;
import com.itheima.lottery.bean.User;
import com.itheima.lottery.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
//评论相关的
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping("/add")
    public String add(String nid, String content, HttpServletRequest request,HttpSession session){
        User user = (User) session.getAttribute("user");
        //1.接收参数
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setIpaddress(request.getRemoteAddr());
        comment.setNid(nid);
        comment.setTime(new Date().toLocaleString()); // 2018-06-26
        comment.setUsername(user.getUsername());

        //2.调用业务保存
        commentService.save(comment);

        //3.生成响应
        return "redirect:/note/detail?nid="+nid;
    }
}
