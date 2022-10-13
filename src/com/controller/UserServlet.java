package com.controller;

import com.service.UserService;
import com.service.impl.UserServiceImpl;

public class UserServlet extends ViewBaseServlet{
    private final UserService userService = new UserServiceImpl();
}
