package com.service;

import com.entity.Order;
import com.entity.OrderDetail;

import java.util.List;

public interface OrderService {
    // 查找指定用户的订单
    List<Order> findUserOrder(Integer userId);
    // 查找所有用户的订单
    List<Order> findAllOrder(Integer pageNo);
    // 查找订单的详细信息
    List<OrderDetail> findOrderDetail(String orderId);
    // 查找订单的总记录条数
    int findAllOrderCount();
    // 添加订单到指定用户
    void addOrder(Order order);
    // 添加订单详情到指定用户
    void addOrderDetail(OrderDetail od);
    // 修改订单发货状态
    void updateStatus(Integer status, String orderId);
    // 删除订单
    void deleteOrder(String orderId);
}
