package com.itheima.lottery.service;

import com.itheima.lottery.bean.BallHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HistoryService {
    List<BallHistory> findAll();
    public Page<BallHistory> findAll(Pageable pageable);

    BallHistory findOne(String code);
}
