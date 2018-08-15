package com.itheima.lottery.dao;

import com.itheima.lottery.bean.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao extends JpaRepository<Order,String> {
    List<Order> findByUid(String uid);
}
