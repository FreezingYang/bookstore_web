package com.service.impl;

import com.dao.BookDao;
import com.dao.impl.BookDaoImpl;
import com.entity.Book;
import com.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private final BookDao bookDao = new BookDaoImpl();

    @Override
    public int addBookList(Book book) {
        return bookDao.addBookList(book);
    }

    @Override
    public int deleteBookList(Integer bookId) {
        return bookDao.deleteBookList(bookId);
    }

    @Override
    public int updateBookList(Book book) {
        return bookDao.updateBookList(book);
    }

    @Override
    public int findAllBookCount() {
        return bookDao.findAllBookCount();
    }

    @Override
    public int findAllBookCount(Integer min, Integer max) {
        return bookDao.findAllBookCount(min, max);
    }

    @Override
    public List<Book> findAllBookList(Integer min, Integer max, Integer pageNo) {
        return bookDao.findAllBookList(min, max, pageNo);
    }

    @Override
    public List<Book> findAllBookList(Integer pageNo) {
        return bookDao.findAllBookList(pageNo);
    }

    @Override
    public double findMaxPrice() {
        return bookDao.findMaxPrice();
    }

    @Override
    public Book findAllBookById(Integer bookId) {
        return bookDao.findAllBookById(bookId);
    }
}
