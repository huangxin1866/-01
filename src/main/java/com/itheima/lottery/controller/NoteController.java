package com.itheima.lottery.controller;

import com.itheima.lottery.bean.Note;
import com.itheima.lottery.bean.User;
import com.itheima.lottery.service.NoteService;
import com.itheima.lottery.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteService noteService;


    //查询所有的帖子
    @GetMapping("/bbs_index") //  /note/bbs_index
    public String bbs_index(Model model){

        //调用业务查询所有帖子
        List<Note> notes = noteService.findAll();
        model.addAttribute("notes",notes);
        return "bbs_index";
    }

    @PostMapping("/publish")
    public String publish(Note note, HttpSession session, HttpServletRequest request){
        //1.接收参数
        //封装note中的剩余参数
        User user = (User) session.getAttribute("user");

        note.setUsername(user.getUsername());
        //获取请求者的IP地址
        note.setIpaddress(request.getRemoteAddr());
        note.setNid(UUIDUtils.getId());
        note.setTime(new Date().toLocaleString());

        //2.调用业务保存帖子
        noteService.save(note);

        //3.生成响应: 帖子列表
        return "redirect:/note/bbs_index";
        /**
         *  insert 保存 / 删除 / 更新 : 重定向
         *  查询: 转发
         */
    }
    //帖子详情连接
    @GetMapping("/detail")
    public String detail(String nid,Model model){
        //1.接收参数

        //2.调用业务
        Note note = noteService.findOne(nid);

        //3.生成响应:去到帖子详情页面
        model.addAttribute("note",note);
        return "bbs_detail";
    }
}
