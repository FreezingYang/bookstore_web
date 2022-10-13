package com.entity;

import java.math.BigDecimal;

public class Book {
    private Integer bookId;
    private String name;
    private BigDecimal price;
    private String author;
    private Integer stock;
    private String imgPath;

    public Book() {
    }

    public Book(Integer bookId, String name, BigDecimal price, String author, Integer stock, String imgPath) {
        this.bookId = bookId;
        this.name = name;
        this.price = price;
        this.author = author;
        this.stock = stock;
        this.imgPath = imgPath;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", author='" + author + '\'' +
                ", stock=" + stock +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }
}
