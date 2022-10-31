package com.dao.impl;

import com.dao.BookDao;
import com.entity.Book;
import com.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class BookDaoImpl implements BookDao {
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDateSource());
    @Override
    public int addBookList(Book book) {
        String sql = "insert into t_book(name, price, author, sales, stock, img_path) values (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, book.getName(), book.getPrice(), book.getAuthor(), book.getSales(), book.getStock(), book.getImgPath());
    }

    @Override
    public int deleteBookList(Integer bookId) {
        String cartSql = "delete from t_cart_item where book_id = ?";
        jdbcTemplate.update(cartSql, bookId);
        String sql = "delete from t_book where book_id = ?";
        return jdbcTemplate.update(sql, bookId);
    }

    @Override
    public int updateBookList(Book book) {
        String sql = "update t_book set price = ?, sales = ?, stock = ? where book_id = ?";
        return jdbcTemplate.update(sql, book.getPrice(), book.getSales(), book.getStock(), book.getBookId());
    }

    @Override
    public int findAllBookCount() {
        String sql = "select count(*) from t_book";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public int findAllBookCount(Integer min, Integer max) {
        String sql = "select count(*) from t_book where price between ? and ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, min, max);
    }

    @Override
    public List<Book> findAllBookList(Integer min, Integer max, Integer pageNo) {
        String sql = "select * from t_book where price between ? and ? limit ?, 10";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class), min, max, (pageNo-1)*10);
    }

    @Override
    public List<Book> findAllBookList(Integer pageNo) {
        String sql = "select * from t_book limit ?, 10";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class), (pageNo-1)*10);
    }

    @Override
    public double findMaxPrice() {
        String sql = "select max(price) from t_book";
        return jdbcTemplate.queryForObject(sql, Double.class);
    }

    @Override
    public Book findAllBookById(Integer bookId) {
        String sql = "select * from t_book where book_id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Book.class), bookId);
    }
}