package com.dao;

import com.entity.User;

public interface UserDao {


    // 用户注册方法
    int addBookUser(User bookUser);

    // 通过用户名称和密码查询用户的方法
    int findUserByNameAndPassword(String userName,String password);

}
