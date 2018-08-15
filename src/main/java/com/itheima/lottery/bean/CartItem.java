package com.itheima.lottery.bean;

import lombok.Data;

/**
 *  购物项对象: 购物车的一箱内容
         红球
         蓝球
         购买了几注
         单注的金额
         小计  : 几注 * 单注的金额

 */
@Data
public class CartItem {

    private String red;

    private String blue;

    private int count;

    private double price=2;

    private double subtotal;

    public double getSubtotal(){
        return price*count;
    }
}
