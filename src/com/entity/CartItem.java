package com.entity;

public class CartItem {
    private Integer id;
    private Integer bookId;
    private Book book;
    private Integer buyCount;
    private Double sum;
    private Integer userId;

    public CartItem(){

    }

    public CartItem(Integer bookId, Integer buyCount, Integer userId) {
        this.bookId = bookId;
        this.buyCount = buyCount;
        this.userId = userId;
    }

    public CartItem(Integer id, Integer bookId, Book book, Integer buyCount, Double sum, Integer userId) {
        this.id = id;
        this.bookId = bookId;
        this.book = book;
        this.buyCount = buyCount;
        this.sum = sum;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", book=" + book +
                ", buyCount=" + buyCount +
                ", sum=" + sum +
                ", userId=" + userId +
                '}';
    }
}
