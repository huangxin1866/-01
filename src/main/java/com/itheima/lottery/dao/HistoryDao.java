package com.itheima.lottery.dao;

import com.itheima.lottery.bean.BallHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryDao extends JpaRepository<BallHistory,String> {
}
