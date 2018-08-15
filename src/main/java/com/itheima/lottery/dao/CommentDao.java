package com.itheima.lottery.dao;

import com.itheima.lottery.bean.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDao extends JpaRepository<Comment,Integer> {

}
