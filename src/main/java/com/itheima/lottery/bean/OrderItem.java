package com.itheima.lottery.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class OrderItem {
    //0.订单项的编号
    @Id
    private String itemid;
    //1.购买的是哪一期
    private String code;
    //2.购买红球的号码
    private String red;
    //3.购买的蓝球号码
    private String blue;
    //4.购买的数量
    private int count;
    //6.这一项是属于哪个订单的
    private String oid;
}
