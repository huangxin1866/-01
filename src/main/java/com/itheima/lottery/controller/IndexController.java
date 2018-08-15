package com.itheima.lottery.controller;

import com.itheima.lottery.bean.BallHistory;
import com.itheima.lottery.bean.Note;
import com.itheima.lottery.service.HistoryService;
import com.itheima.lottery.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 首页模块
 */
@Controller
public class IndexController {

    @Autowired
    private NoteService noteService;
    @Autowired
    private HistoryService historyService;
    @GetMapping("/")
    public String index(@RequestParam(defaultValue = "0") int pageNumber, Model model){
        //1.接收参数
        //2.调用业务处理 : 查询所有的历史记录
        //List<BallHistory> historyList = historyService.findAll();
        Sort sort = new Sort(Sort.Direction.DESC, "code");
        /**
         * page  页码 从0开始
         * size  每页的大小
         * sort  排序规则
         */
        Pageable pageable = new PageRequest(pageNumber,10,sort);
        Page<BallHistory> page = historyService.findAll(pageable);

        System.out.println("是否为首页："+page.isFirst());
        System.out.println("当前请求的页码："+page.getNumber());
        System.out.println("总页数："+page.getTotalPages());
        System.out.println("是否为最后一页"+page.isLast());


        List<BallHistory> historyList = page.getContent();
        // System.out.println(historyList.size());

        //查询最新的帖子
        Note note = noteService.findNew();

        //3.生成响应: 将历史记录响应给用户
        model.addAttribute("historyList",historyList);
        model.addAttribute("note",note);

        model.addAttribute("page",page);

        return "index";
    }

    @GetMapping("/detail")
    public String detail(String code,Model model){
        //1.接收参数
        //2.调用业务
        BallHistory history = historyService.findOne(code);
        //3.生成响应
        model.addAttribute("history",history);
        return "detail";
    }

}
