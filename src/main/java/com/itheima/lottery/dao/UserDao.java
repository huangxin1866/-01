package com.itheima.lottery.dao;

import com.itheima.lottery.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface UserDao extends JpaRepository<User,String> {
    User findByEmailAndPassword(String email, String password);
}
