package com.itheima.lottery.service;

import com.itheima.lottery.bean.User;

public interface UserService {
    void doRegister(User user);

    User doLogin(String email, String password);

    void update(User user);
}
