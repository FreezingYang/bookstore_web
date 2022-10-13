package com.service.impl;

import com.dao.BookDao;
import com.dao.impl.BookDaoImpl;
import com.service.BookService;

public class BookServiceImpl implements BookService {
    private final BookDao bookDao = new BookDaoImpl();
}
