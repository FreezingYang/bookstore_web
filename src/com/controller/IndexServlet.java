package com.controller;

import com.entity.Book;
import com.service.BookService;
import com.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IndexServlet extends ViewBaseServlet {
    private final BookService bookService = new BookServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.findAllBookList(req, resp);
//        processTemplate("index",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }

    protected void findAllBookList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = 1;
        int min = 0;
        String pageNoStr = req.getParameter("pageNo");
        if (pageNoStr != null){
            pageNo = Integer.parseInt(pageNoStr);
        }
        // 设置会话域
        HttpSession session = req.getSession();
        session.setAttribute("pageNo", pageNo);
        // 获取价格最高值
        double max = bookService.findMaxPrice();
        String maxPrice = req.getParameter("max");
        String minPrice = req.getParameter("min");
        if (maxPrice != null){
            max = Double.parseDouble(maxPrice);
        }
        if (minPrice != null){
            min = Integer.parseInt(minPrice);
        }
        // 总记录条数
        int bookCount = bookService.findAllBookCount(min, (int) Math.ceil(max));
        session.setAttribute("bookCount", bookCount);
        // 总页数 (总记录条数 + 显示条数 - 1)/显示条数
        int pageCount = (bookCount + 10 - 1)/10;
        // 页码
        List<Integer> pageList = new ArrayList<>();
        for (int i = 1; i < pageCount + 1; i++) {
            pageList.add(i);
        }
        session.setAttribute("pageList", pageList);
        session.setAttribute("pageCount", pageCount);
        // 分页查询
        List<Book> bookList = bookService.findAllBookList(min, (int) Math.ceil(max), pageNo);
        req.setAttribute("bookList", bookList);
        session.setAttribute("min", min);
        session.setAttribute("max", max);
        processTemplate("index", req, resp);
    }
}
