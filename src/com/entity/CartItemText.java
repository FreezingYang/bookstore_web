package com.entity;

public class CartItemText {
    private Integer id;
    private Book book;
    private Integer buyCount;
    private Integer user;

    public CartItemText() {
    }

    public CartItemText(Integer id, Book book, Integer buyCount, Integer user) {
        this.id = id;
        this.book = book;
        this.buyCount = buyCount;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "CartItemText{" +
                "id=" + id +
                ", book=" + book +
                ", buyCount=" + buyCount +
                ", user=" + user +
                '}';
    }
}
