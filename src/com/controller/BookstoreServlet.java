package com.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BookstoreServlet extends ViewBaseServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        if ("User".equals(req.getParameter("method").toString())){
            processTemplate("pages/user/login",req,resp);
        }
        if ("Regist".equals(req.getParameter("method").toString())){
            processTemplate("pages/user/regist",req,resp);
        }
        if ("cart".equals(req.getParameter("method").toString())){
            processTemplate("pages/cart/cart",req,resp);
        }
        if ("checkout".equals(req.getParameter("method").toString())){
            processTemplate("pages/cart/checkout",req,resp);
        }
        if ("Bookmanager".equals(req.getParameter("method").toString())){
            processTemplate("pages/manager/book_manager",req,resp);
        }
        if ("Order".equals(req.getParameter("method").toString())){
            processTemplate("pages/order/order",req,resp);
        }
        if ("index".equals(req.getParameter("method").toString())){
            processTemplate("index",req,resp);
        }
        if ("registSuccess".equals(req.getParameter("method").toString())){
            processTemplate("pages/user/regist_success",req,resp);
        }
        if ("order_manager".equals(req.getParameter("method").toString())){
            processTemplate("pages/manager/order_manager",req,resp);
        }
        if ("book_edit".equals(req.getParameter("method").toString())){
            processTemplate("pages/manager/book_edit",req,resp);
        }
        if ("book_manager".equals(req.getParameter("method").toString())){
            processTemplate("pages/manager/book_manager",req,resp);
        }

    }
}
