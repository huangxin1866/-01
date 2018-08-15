package com.itheima.lottery.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;


@Entity
@Data
public class Note {
    //帖子的编号
    @Id
    private String nid;
    //帖子的标题
    private String title;
    //帖子的内容
    private String content;
    //发帖的时间

    private String time;
    //谁发的帖子
    private String username;

    //发帖人的IP地址
    private String ipaddress;

    //保存当前帖子里面的所有评论
    @OneToMany(mappedBy = "nid")
    private List<Comment> comments;
}