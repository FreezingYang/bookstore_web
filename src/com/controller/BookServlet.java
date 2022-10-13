package com.controller;

import com.service.BookService;
import com.service.impl.BookServiceImpl;

public class BookServlet extends ViewBaseServlet{
    private final BookService bookService = new BookServiceImpl();
}
