package com.service.impl;

import com.dao.OrderDao;
import com.dao.impl.OrderDaoImpl;
import com.entity.Order;
import com.entity.OrderDetail;
import com.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao = new OrderDaoImpl();
    @Override
    public List<Order> findUserOrder(Integer userId) {
        return orderDao.findUserOrder(userId);
    }

    @Override
    public List<Order> findAllOrder(Integer pageNo) {
        return orderDao.findAllOrder(pageNo);
    }

    @Override
    public void updateStatus(Integer status, String orderId) {
        orderDao.updateStatus(status, orderId);
    }

    @Override
    public void deleteOrder(String orderId) {
        orderDao.deleteOrder(orderId);
    }

    @Override
    public List<OrderDetail> findOrderDetail(String orderId) {
        return orderDao.findOrderDetail(orderId);
    }

    @Override
    public int findAllOrderCount() {
        return orderDao.findAllOrderCount();
    }

    @Override
    public void addOrder(Order order) {
        orderDao.addOrder(order);
    }

    @Override
    public void addOrderDetail(OrderDetail od) {
        orderDao.addOrderDetail(od);
    }
}
