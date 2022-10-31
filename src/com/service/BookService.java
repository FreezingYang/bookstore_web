package com.service;

import com.entity.Book;

import java.util.List;

public interface BookService {
    // 添加图书
    int addBookList(Book book);

    // 删除图书: (先删除购物车中的该图书)
    int deleteBookList(Integer bookId);

    // 修改图书:
    int updateBookList(Book book);

    // 查询全部图书记录总条数
    int findAllBookCount();

    // 查询在什么价格区间的图书记录总条数
    int findAllBookCount(Integer min, Integer max);

    // 查询全部图书:(分页查询,每页10条数据)
    List<Book> findAllBookList(Integer pageNo, Integer min, Integer max);

    // 查询全部图书:(分页查询,每页10条数据)
    List<Book> findAllBookList(Integer pageNo);

    // 查询图书最高价格
    double findMaxPrice();

    // 通过bookId查找图书信息
    Book findAllBookById(Integer bookId);
}
