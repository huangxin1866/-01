package com.itheima.lottery.service.impl;

import com.itheima.lottery.bean.Comment;
import com.itheima.lottery.dao.CommentDao;
import com.itheima.lottery.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public void save(Comment comment) {
        commentDao.save(comment);
    }
}
