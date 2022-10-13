package com.dao;

import com.entity.Book;

import java.util.List;

public interface BookDao {


    // 添加图书
    int addBookList(Book bookList);

    // 删除图书: (先删除购物车中的该图书)
    int deleteBookList(Integer bookId);

    // 修改图书:
    int updateBookList(Book bookList);

    // 查询全部图书记录总条数
    int findAllBookCount();
    // 查询全部图书:(分页查询,每页10条数据)
    List<Book> findAllBookList(Integer pageNo);
}
