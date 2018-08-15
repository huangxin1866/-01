package com.itheima.lottery.service.impl;

import com.itheima.lottery.bean.BallHistory;
import com.itheima.lottery.dao.HistoryDao;
import com.itheima.lottery.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistorySeviceImpl implements HistoryService {

    @Autowired
    private HistoryDao historyDao;

    @Override
    public List<BallHistory> findAll() {
        //查询所有的历史记录的
        Sort sort = new Sort(Sort.Direction.DESC, "code");
        return historyDao.findAll(sort);
    }

    public Page<BallHistory> findAll(Pageable pageable){
        Page<BallHistory> page = historyDao.findAll(pageable);
        return page;
    }

    @Override
    public BallHistory findOne(String code) {
        return historyDao.findOne(code);
    }
}
