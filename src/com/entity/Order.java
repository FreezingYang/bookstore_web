package com.entity;

import java.math.BigDecimal;

public class Order {
    private Integer orderId;
    private Integer orderCount;
    private BigDecimal orderAccount;
    private Integer userID;
    private Integer orderStatus;

    public Order() {
    }

    public Order(Integer orderId, Integer orderCount, BigDecimal orderAccount, Integer userID, Integer orderStatus) {
        this.orderId = orderId;
        this.orderCount = orderCount;
        this.orderAccount = orderAccount;
        this.userID = userID;
        this.orderStatus = orderStatus;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public BigDecimal getOrderAccount() {
        return orderAccount;
    }

    public void setOrderAccount(BigDecimal orderAccount) {
        this.orderAccount = orderAccount;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderCount=" + orderCount +
                ", orderAccount=" + orderAccount +
                ", userID=" + userID +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
