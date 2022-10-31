package com.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private String orderId;
    private Integer orderCount;
    private BigDecimal orderAmount;
    private Integer userId;
    private Integer orderStatus;
    private Date createTime;

    public Order() {
    }

    public Order(String orderId, Integer orderCount, BigDecimal orderAmount, Integer userId, Integer orderStatus) {
        this.orderId = orderId;
        this.orderCount = orderCount;
        this.orderAmount = orderAmount;
        this.userId = userId;
        this.orderStatus = orderStatus;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Integer getUserID() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", orderCount=" + orderCount +
                ", orderAmount=" + orderAmount +
                ", userId=" + userId +
                ", orderStatus=" + orderStatus +
                ", createTime=" + createTime +
                '}';
    }
}
