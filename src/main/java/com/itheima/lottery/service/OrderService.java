package com.itheima.lottery.service;

import com.itheima.lottery.bean.Order;

import java.util.List;

public interface OrderService {
    void save(Order order);

    List<Order> findByUid(String uid);
}
