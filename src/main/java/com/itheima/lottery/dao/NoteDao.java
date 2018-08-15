package com.itheima.lottery.dao;

import com.itheima.lottery.bean.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteDao extends JpaRepository<Note,String> {
    //自定义查询最新的帖子的方法
    Note findTopByOrderByTimeDesc();
}
