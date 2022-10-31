package com.service;

import com.entity.User;

public interface UserService {
    // 用户注册方法
    void addBookUser(User user);

    // 通过用户名称和密码查询用户的方法
    User findUserByNameAndPassword(String userName, String password);

    // 通过用户名称查询用户的方法
    int findUserByName(String userName);
}
