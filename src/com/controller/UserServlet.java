package com.controller;

import com.entity.CartItem;
import com.entity.User;
import com.service.CartService;
import com.service.UserService;
import com.service.impl.CartServiceImpl;
import com.service.impl.UserServiceImpl;
import org.springframework.util.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class UserServlet extends ViewBaseServlet{
    private final UserService userService = new UserServiceImpl();
    private final CartService cartService = new CartServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        if (req.getParameter("method").equals("login")){
            processTemplate("pages/user/login", req, resp);
        }
        if (req.getParameter("method").equals("regist")){
            processTemplate("pages/user/regist", req, resp);
        }
        if (req.getParameter("method").equals("findUser")){
            this.findUserByNameAndPassword(req,resp);
        }
        if (req.getParameter("method").equals("addUser")){
            this.addUser(req,resp);
        }
        if (req.getParameter("method").equals("findUserByName")){
            this.findUserByName(req,resp);
        }
        if (req.getParameter("method").equals("logout")){
            // 退出登录功能，重定向到index.do
            HttpSession session = req.getSession();
            session.setAttribute("user", null);
            resp.sendRedirect("/index.do");
        }
    }

    // 注册用户
    protected void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("user");
        String password = req.getParameter("password");
        String hashedPwd1 = DigestUtils.md5DigestAsHex((password).getBytes());
        String email = req.getParameter("email");

        User user = new User(userName,  hashedPwd1, email, 0);

        userService.addBookUser(user);
        HttpSession session = req.getSession();

        User user1 = userService.findUserByNameAndPassword(userName, hashedPwd1);
        cartService.getCartItemList(user1);
        session.setAttribute("user", user1);

        processTemplate("pages/user/regist_success", req, resp);
    }

    // 登录验证判断
    protected void findUserByNameAndPassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String hashedPwd1 = DigestUtils.md5DigestAsHex((password).getBytes());
        User user = userService.findUserByNameAndPassword(username, hashedPwd1);
        HttpSession session = req.getSession();

        if (user != null) {
            session.setAttribute("user", user);
            List<CartItem> cartItemList = cartService.getCartItemList(user);
            processTemplate("/pages/user/login_success", req, resp);
        } else {
            resp.getWriter().write("" + null);
        }
    }

    // 注册账号时，判断数据库中是否存在相同的用户名
    protected void findUserByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("user");
        int i = userService.findUserByName(userName);
        if (i == 0){
            System.out.println("没有该用户");
        }else {
            resp.getWriter().write("" + i);
        }
    }
}
