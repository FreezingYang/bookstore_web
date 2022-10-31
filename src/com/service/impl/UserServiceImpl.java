package com.service.impl;

import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import com.entity.User;
import com.service.UserService;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();

    @Override
    public void addBookUser(User user) {
        userDao.addBookUser(user);
    }

    @Override
    public User findUserByNameAndPassword(String userName, String password) {
        return userDao.findUserByNameAndPassword(userName, password);
    }

    @Override
    public int findUserByName(String userName) {
        return userDao.findUserByName(userName);
    }
}
