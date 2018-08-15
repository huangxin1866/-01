package com.itheima.lottery.service.impl;

import com.itheima.lottery.bean.User;
import com.itheima.lottery.dao.UserDao;
import com.itheima.lottery.service.UserService;
import com.itheima.lottery.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;
    @Override
    public void doRegister(User user) {
        String password = user.getPassword();
        String newPassword = MD5Utils.encode(password,"@#$%^^&");
        user.setPassword(newPassword);
        userDao.save(user);
    }

    @Override  // 123456
    public User doLogin(String email, String password) {
        password = MD5Utils.encode(password,"@#$%^^&");
        return userDao.findByEmailAndPassword(email,password);
    }

    @Override
    public void update(User user) {
        userDao.save(user);
    }
}
