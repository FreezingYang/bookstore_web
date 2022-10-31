package com.entity;

import java.math.BigDecimal;

public class OrderDetail {
    private Integer itemId;
    private Integer bookId;
    private String imgPath;
    private String bookName;
    private Integer bookCount;
    private BigDecimal amount;
    private String orderId;
    private Integer orderStatus;

    public OrderDetail(){

    }

    public OrderDetail(Integer itemId, Integer bookId, String imgPath, String bookName, Integer bookCount, BigDecimal amount, String orderId, Integer orderStatus) {
        this.itemId = itemId;
        this.bookId = bookId;
        this.imgPath = imgPath;
        this.bookName = bookName;
        this.bookCount = bookCount;
        this.amount = amount;
        this.orderId = orderId;
        this.orderStatus = orderStatus;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getBookCount() {
        return bookCount;
    }

    public void setBookCount(Integer bookCount) {
        this.bookCount = bookCount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "itemId=" + itemId +
                ", bookId=" + bookId +
                ", imgPath='" + imgPath + '\'' +
                ", bookName='" + bookName + '\'' +
                ", bookCount=" + bookCount +
                ", amount=" + amount +
                ", orderId='" + orderId + '\'' +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
