package com.dao.impl;

import com.dao.OrderDao;
import com.entity.Order;
import com.entity.OrderDetail;
import com.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDateSource());
    @Override
    public List<Order> findUserOrder(Integer userId) {
        String sql = "select * from t_order where user_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Order.class), userId);
    }

    @Override
    public List<Order> findAllOrder(Integer pageNo) {
        String sql = "select * from t_order order by update_time desc limit ?, 10";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Order.class), (pageNo-1)*10);
    }

    @Override
    public List<OrderDetail> findOrderDetail(String orderId) {
        String sql = "select * from t_order_detail where order_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(OrderDetail.class), orderId);
    }

    @Override
    public int findAllOrderCount() {
        String sql = "select count(order_id) from t_order";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public void addOrder(Order order) {
        String sql = "insert into t_order(order_id, order_count, order_amount, user_id, order_status) values(?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, order.getOrderId(), order.getOrderCount(), order.getOrderAmount(), order.getUserID(), order.getOrderStatus());
    }

    @Override
    public void addOrderDetail(OrderDetail od) {
        String sql = "insert into t_order_detail(book_id, img_path, book_name, book_count, amount, order_id, order_status) values(?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, od.getBookId(), od.getImgPath(), od.getBookName(), od.getBookCount(), od.getAmount(), od.getOrderId(), od.getOrderStatus());
    }

    @Override
    public void updateStatus(Integer status, String orderId) {
        String sql = "update t_order set order_status = ? where order_id = ?";
        jdbcTemplate.update(sql, status, orderId);
        String detailSql = "update t_order_detail set order_status = ? where order_id = ?";
        jdbcTemplate.update(detailSql, status, orderId);
    }

    @Override
    public void deleteOrder(String orderId) {
        String detailSql = "delete from t_order_detail where order_id = ?";
        jdbcTemplate.update(detailSql, orderId);
        String sql = "delete from t_order where order_id = ?";
        jdbcTemplate.update(sql, orderId);
    }
}
