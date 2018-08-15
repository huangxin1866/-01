package com.itheima.lottery.service;

import com.itheima.lottery.bean.Note;

import java.util.List;

public interface NoteService {
    void save(Note note);

    List<Note> findAll();

    Note findOne(String nid);
    //查询最新的一个帖子
    Note findNew();
}
